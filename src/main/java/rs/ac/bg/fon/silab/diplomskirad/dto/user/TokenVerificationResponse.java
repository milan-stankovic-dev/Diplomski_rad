package rs.ac.bg.fon.silab.diplomskirad.dto.user;

import io.jkratz.mediator.core.Event;

public record TokenVerificationResponse(
        boolean verified
) implements Event { }
