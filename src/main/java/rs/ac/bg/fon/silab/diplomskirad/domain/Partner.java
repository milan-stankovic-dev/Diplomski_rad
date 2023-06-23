package rs.ac.bg.fon.silab.diplomskirad.domain;

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
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String businessPartnerAddress;
    private String businessPartnerName;

}
