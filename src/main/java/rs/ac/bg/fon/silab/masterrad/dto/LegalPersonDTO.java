package rs.ac.bg.fon.silab.masterrad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
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