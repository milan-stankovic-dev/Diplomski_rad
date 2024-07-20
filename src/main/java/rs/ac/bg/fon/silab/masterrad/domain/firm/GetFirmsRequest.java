package rs.ac.bg.fon.silab.masterrad.domain.firm;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.FirmDTO;

public record GetFirmsRequest
        () implements Request<DTOListResponse<FirmDTO>> { }
