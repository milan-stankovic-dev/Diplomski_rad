package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_natural_person")
public class NaturalPerson extends Buyer {
    @NotEmpty(message = "You must input the buyer's name.")
    private String buyerName;
    @NotEmpty(message = "You must input the buyer's last name.")
    private String buyerLastName;

    public NaturalPerson(long id, String buyerAddress,
                       String buyerName, String buyerLastName) {
        super(id, buyerAddress);
        this.buyerName = buyerName;
        this.buyerLastName = buyerLastName;
    }
}
