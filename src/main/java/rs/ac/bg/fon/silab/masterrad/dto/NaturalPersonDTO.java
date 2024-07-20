package rs.ac.bg.fon.silab.masterrad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPersonDTO
        extends BuyerDTO{
        private String name;
        private String lastName;

        public NaturalPersonDTO(long id, String buyerName,
                                String buyerLastName) {
                this.id = id;
                this.name = buyerName;
                this.lastName = buyerLastName;
        }
}
