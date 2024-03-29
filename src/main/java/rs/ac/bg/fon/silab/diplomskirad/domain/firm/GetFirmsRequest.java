package rs.ac.bg.fon.silab.diplomskirad.domain.firm;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;

public record GetFirmsRequest
        () implements Request<DTOListResponse<FirmDTO>> { }
