package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GoodsReceivedNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected Date deadLine;
    protected Date issueDate;
    protected BigDecimal totalCost;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "note")
    @JsonIgnore
    private List<GoodsReceivedNoteItem> items;


}
