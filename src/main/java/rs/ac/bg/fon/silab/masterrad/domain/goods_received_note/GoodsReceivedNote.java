package rs.ac.bg.fon.silab.masterrad.domain.goods_received_note;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.masterrad.domain.abstraction.BusinessDocument;
import rs.ac.bg.fon.silab.masterrad.domain.goods_received_note_item.GoodsReceivedNoteItem;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_goods_received_note")
public class GoodsReceivedNote implements BusinessDocument<GoodsReceivedNoteItem> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @FutureOrPresent(message = "Deadline must be set in the future or the current date.")
    protected Date deadLine;

    @PastOrPresent(message = "Issue date can only be in the past or today.")
    protected Date issueDate;

    @DecimalMin(value = "0", inclusive = false)
    protected BigDecimal totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<GoodsReceivedNoteItem> items;


    @Override
    public BigDecimal getTotalValue() {
        return totalCost;
    }

}
