package rs.ac.bg.fon.silab.diplomskirad.domain;

import io.jkratz.mediator.core.Event;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;

import java.util.List;

public record DTOListResponse<TYPE>(
        List<TYPE> dtos
) implements Event
{ }
