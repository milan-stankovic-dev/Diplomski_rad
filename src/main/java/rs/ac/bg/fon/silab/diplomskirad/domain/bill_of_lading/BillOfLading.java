package rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.BusinessDocument;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading_item.BillOfLadingItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_bill_of_lading")
public class BillOfLading implements BusinessDocument<BillOfLadingItem> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @FutureOrPresent(message = "Deadline must be set in the future or the current date.")
    protected Date deadLine;

    @PastOrPresent(message = "Issue date can only be in the past or today.")
    protected Date issueDate;

    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "1000000")
    protected BigDecimal totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BillOfLadingItem> items;

    @Override
    public BigDecimal getTotalValue() {
        return totalCost;
    }

}
