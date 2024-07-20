package rs.ac.bg.fon.silab.masterrad.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LegalPersonDTO.class, name = "legalPerson"),
        @JsonSubTypes.Type(value = NaturalPersonDTO.class, name = "naturalPerson")
})
public abstract class BuyerDTO {
    protected long id;
}
