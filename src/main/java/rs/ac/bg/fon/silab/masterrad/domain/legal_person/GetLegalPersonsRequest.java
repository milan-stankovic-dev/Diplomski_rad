package rs.ac.bg.fon.silab.masterrad.domain.legal_person;


import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.LegalPersonDTO;

public record GetLegalPersonsRequest
        () implements Request<DTOListResponse<LegalPersonDTO>> { }
