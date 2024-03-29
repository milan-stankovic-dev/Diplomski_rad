package rs.ac.bg.fon.silab.diplomskirad.domain.natural_person;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;

public record GetNaturalPersonsRequest
        () implements Request<DTOListResponse<NaturalPersonDTO>> { }
