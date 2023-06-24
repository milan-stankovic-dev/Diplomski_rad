package rs.ac.bg.fon.silab.diplomskirad.mapper;

import rs.ac.bg.fon.silab.diplomskirad.domain.Partner;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;

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
