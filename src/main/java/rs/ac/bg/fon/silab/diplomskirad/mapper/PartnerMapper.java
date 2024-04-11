package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.partner.Partner;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;
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
