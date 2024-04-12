package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note_item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.BusinessDocumentItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_goods_received_note_item")
public class GoodsReceivedNoteItem implements BusinessDocumentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "You must order at least one item")
    private int amountOrdered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public int getAmountTransacted() {
        return amountOrdered;
    }
}
