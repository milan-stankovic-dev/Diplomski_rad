package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillOfLading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @FutureOrPresent(message = "Deadline must be set in the future or the current date.")
    protected Date deadLine;

    @PastOrPresent(message = "Issue date can only be in the past or today.")
    protected Date issueDate;

    @Min(value = 0, message = "Total cost must be a positive number.")
    protected BigDecimal totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BillOfLadingItem> items;

}
