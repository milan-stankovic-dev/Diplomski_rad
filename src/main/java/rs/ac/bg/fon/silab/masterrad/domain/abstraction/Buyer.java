package rs.ac.bg.fon.silab.masterrad.domain.abstraction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
