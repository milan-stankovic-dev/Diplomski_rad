package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NaturalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String buyerName;
    private String buyerLastName;

}
