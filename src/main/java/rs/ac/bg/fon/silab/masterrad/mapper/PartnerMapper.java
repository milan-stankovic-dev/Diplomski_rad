package rs.ac.bg.fon.silab.masterrad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.dto.PartnerDTO;

@Component
public non-sealed class PartnerMapper implements DtoDomainMapper<PartnerDTO, Partner> {
    @Override
    public PartnerDTO entityToDTO(Partner partner) {
        var partnerDTO = new PartnerDTO(
                partner.getId(),
                partner.getBusinessPartnerName()
        );

        return partnerDTO;
    }

    @Override
    public Partner dTOtoEntity(PartnerDTO partnerDTO) {
        var partner = new Partner();

        partner.setId(partnerDTO.id());
        partner.setBusinessPartnerName(partner.getBusinessPartnerName());

        return partner;
    }
}
