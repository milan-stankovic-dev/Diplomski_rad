package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int amountOrdered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

}
