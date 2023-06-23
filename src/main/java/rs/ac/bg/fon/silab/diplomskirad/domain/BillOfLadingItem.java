package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillOfLadingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private BillOfLading bill;
    protected int amountSold;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    protected Product product;
}
