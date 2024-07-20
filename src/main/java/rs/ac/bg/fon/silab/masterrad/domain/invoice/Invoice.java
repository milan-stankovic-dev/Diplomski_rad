package rs.ac.bg.fon.silab.masterrad.domain.invoice;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.masterrad.domain.invoice_item.InvoiceItem;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Future(message = "Deadline must be set in the future.")
    private Date deadLine;
    @PastOrPresent(message = "Issue date must be set in the past or present.")
    private Date issueDate;

    @DecimalMin(value = "0", inclusive = false, message = "Total cost must be greater than 0.")
    private BigDecimal totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<InvoiceItem> items;
}
