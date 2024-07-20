package rs.ac.bg.fon.silab.masterrad.domain.partner;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.PartnerDTO;

public record GetPartnersRequest
        () implements Request<DTOListResponse<PartnerDTO>> { }
