package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.NaturalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;
@Component
public non-sealed class NaturalPersonMapper implements DtoDomainMapper<NaturalPersonDTO, NaturalPerson> {
    @Override
    public NaturalPersonDTO entityToDTO(NaturalPerson naturalPerson) {
        var naturalPersonDTO = new NaturalPersonDTO(
                naturalPerson.getId(),
                naturalPerson.getBuyerName(),
                naturalPerson.getBuyerLastName()
        );

        return naturalPersonDTO;
    }

    @Override
    public NaturalPerson dTOtoEntity(NaturalPersonDTO naturalPersonDTO) {
        var naturalPerson = new NaturalPerson(
                naturalPersonDTO.id(),
                naturalPersonDTO.name(),
                naturalPersonDTO.lastName()
        );

        return naturalPerson;
    }
}
