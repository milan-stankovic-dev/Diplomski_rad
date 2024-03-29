package rs.ac.bg.fon.silab.diplomskirad.dto.user;

import io.jkratz.mediator.core.Event;

public record AuthenticationResponse (
        String token
) implements Event
{ }
