package rs.ac.bg.fon.silab.masterrad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.firm.Firm;
import rs.ac.bg.fon.silab.masterrad.dto.FirmDTO;

@Component
public non-sealed class FirmMapper implements DtoDomainMapper<FirmDTO, Firm> {

    @Override
    public FirmDTO entityToDTO(Firm firm) {
        var firmDto = new FirmDTO(
                firm.getId(),
                firm.getFirmName()
        );

        return firmDto;
    }

    @Override
    public Firm dTOtoEntity(FirmDTO firmDTO) {
        var firm = new Firm();
        firm.setId(firmDTO.id());
        firm.setFirmName(firmDTO.firmName());

        return firm;
    }
}
