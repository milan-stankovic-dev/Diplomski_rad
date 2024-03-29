package rs.ac.bg.fon.silab.diplomskirad.domain.invoice_item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_invoice_item")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "You must order at least one item.")
    private int amountOrdered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

}
