package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LegalPerson extends Buyer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firmName;
    private Date foundingDate;

}
