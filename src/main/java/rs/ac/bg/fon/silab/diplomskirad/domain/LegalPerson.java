package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_legal_person")
public class LegalPerson extends Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "You must input a firm name.")
    private String firmName;

    @Past(message = "Your firm founding date must be in the past.")
    private Date foundingDate;

}
