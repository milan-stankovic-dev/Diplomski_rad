package rs.ac.bg.fon.silab.diplomskirad.domain.abstraction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.domain.NaturalPerson;
import rs.ac.bg.fon.silab.diplomskirad.mapper.BuyerMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.DtoDomainMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.LegalPersonMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String buyerAddress;

}
