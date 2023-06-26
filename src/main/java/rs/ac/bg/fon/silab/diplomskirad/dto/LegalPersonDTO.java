package rs.ac.bg.fon.silab.diplomskirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalPersonDTO
        extends BuyerDTO{
    private String firmName;

    public LegalPersonDTO(long id, String firmName) {
        this.id = id;
        this.firmName = firmName;
    }
}