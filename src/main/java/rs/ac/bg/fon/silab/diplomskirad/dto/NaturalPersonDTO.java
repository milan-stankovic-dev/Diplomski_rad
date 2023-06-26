package rs.ac.bg.fon.silab.diplomskirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
