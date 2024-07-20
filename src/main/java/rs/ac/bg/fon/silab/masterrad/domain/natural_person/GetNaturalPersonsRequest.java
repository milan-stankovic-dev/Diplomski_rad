package rs.ac.bg.fon.silab.masterrad.domain.natural_person;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.NaturalPersonDTO;

public record GetNaturalPersonsRequest
        () implements Request<DTOListResponse<NaturalPersonDTO>> { }
