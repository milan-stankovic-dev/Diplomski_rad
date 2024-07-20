package rs.ac.bg.fon.silab.masterrad.domain;

import io.jkratz.mediator.core.Event;

import java.util.List;

public record DTOListResponse<TYPE>(
        List<TYPE> dtos
) implements Event
{ }
