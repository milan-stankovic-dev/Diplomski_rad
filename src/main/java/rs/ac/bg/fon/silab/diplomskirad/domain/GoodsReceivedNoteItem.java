package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GoodsReceivedNoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_received_note_id")
    private GoodsReceivedNote note;
    protected int amountOrdered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    protected Product product;

}
