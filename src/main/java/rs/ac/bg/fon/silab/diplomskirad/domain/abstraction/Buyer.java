package rs.ac.bg.fon.silab.diplomskirad.domain.abstraction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String buyerAddress;

}
