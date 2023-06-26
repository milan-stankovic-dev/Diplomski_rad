package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;
import rs.ac.bg.fon.silab.diplomskirad.mapper.DtoDomainMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.LegalPersonMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.NaturalPersonMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NaturalPerson extends Buyer{
    private String buyerName;
    private String buyerLastName;

    public NaturalPerson(long id, String buyerAddress,
                       String buyerName, String buyerLastName) {
        super(id, buyerAddress);
        this.buyerName = buyerName;
        this.buyerLastName = buyerLastName;
    }
}
