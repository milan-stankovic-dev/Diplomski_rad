package rs.ac.bg.fon.silab.diplomskirad.userUtils;

import io.jkratz.mediator.core.Event;

public record TokenValidationResponse(
        boolean valid
) implements Event { }