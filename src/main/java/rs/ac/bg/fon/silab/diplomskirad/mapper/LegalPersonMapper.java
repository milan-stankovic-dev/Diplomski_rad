package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;
@Component
public non-sealed class LegalPersonMapper implements DtoDomainMapper<LegalPersonDTO, LegalPerson> {

    @Override
    public LegalPersonDTO entityToDTO(LegalPerson legalPerson) {
        var legalPersonDTO = new LegalPersonDTO(
                legalPerson.getId(),
                legalPerson.getFirmName()
        );

        return legalPersonDTO;
    }

    @Override
    public LegalPerson dTOtoEntity(LegalPersonDTO legalPersonDTO) {
        var legalPerson = new LegalPerson();

        legalPerson.setId(legalPersonDTO.getId());
        legalPerson.setFirmName(legalPersonDTO.getFirmName());

        return legalPerson;
    }


}
