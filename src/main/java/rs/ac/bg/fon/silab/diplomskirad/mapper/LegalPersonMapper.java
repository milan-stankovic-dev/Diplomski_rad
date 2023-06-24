package rs.ac.bg.fon.silab.diplomskirad.mapper;

import rs.ac.bg.fon.silab.diplomskirad.domain.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;

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

        legalPerson.setId(legalPersonDTO.id());
        legalPerson.setFirmName(legalPersonDTO.firmName());

        return legalPerson;
    }
}
