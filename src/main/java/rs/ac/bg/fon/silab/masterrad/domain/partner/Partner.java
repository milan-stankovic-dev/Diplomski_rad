package rs.ac.bg.fon.silab.masterrad.domain.partner;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_partner")
@Builder
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String businessPartnerAddress;

    @NotEmpty(message = "You must input business partner's name.")
    @NaturalId
    private String businessPartnerName;

}
