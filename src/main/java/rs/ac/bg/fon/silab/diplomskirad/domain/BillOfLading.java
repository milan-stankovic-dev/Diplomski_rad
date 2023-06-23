package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    protected Date deadLine;
    protected Date issueDate;
    protected BigDecimal totalCost;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    @OneToMany(fetch = FetchType.EAGER,
        mappedBy = "bill")
    @JsonIgnore
    private Set<BillOfLadingItem> items;

}
