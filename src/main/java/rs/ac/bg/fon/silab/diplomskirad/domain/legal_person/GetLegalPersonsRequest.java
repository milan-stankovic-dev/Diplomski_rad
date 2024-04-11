package rs.ac.bg.fon.silab.diplomskirad.domain.legal_person;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;

public record GetLegalPersonsRequest
        () implements Request<DTOListResponse<LegalPersonDTO>> { }
