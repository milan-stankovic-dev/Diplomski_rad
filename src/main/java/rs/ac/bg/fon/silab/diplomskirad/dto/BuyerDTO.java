package rs.ac.bg.fon.silab.diplomskirad.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import rs.ac.bg.fon.silab.diplomskirad.mapper.DtoDomainMapper;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LegalPersonDTO.class, name = "legalPerson"),
        @JsonSubTypes.Type(value = NaturalPersonDTO.class, name = "naturalPerson")
})
public abstract class BuyerDTO {
    protected long id;
}
