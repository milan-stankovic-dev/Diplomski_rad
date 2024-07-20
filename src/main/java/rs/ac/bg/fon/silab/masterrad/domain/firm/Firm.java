package rs.ac.bg.fon.silab.masterrad.domain.firm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_firm")
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Firm name must be inputted.")
    @NaturalId
    private String firmName;
    private String firmAddress;

}
