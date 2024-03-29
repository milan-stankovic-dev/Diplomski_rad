package rs.ac.bg.fon.silab.diplomskirad.domain.partner;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;

public record GetPartnersRequest
        () implements Request<DTOListResponse<PartnerDTO>> { }
