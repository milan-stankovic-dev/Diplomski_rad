package rs.ac.bg.fon.silab.masterrad.dto;

import io.jkratz.mediator.core.Event;

public record FirmDTO(
        long id,
        String firmName
) implements Event { }
