package rs.ac.bg.fon.silab.diplomskirad.userUtils;

import io.jkratz.mediator.core.Event;

public record AuthenticationResponse (
        String token
) implements Event
{ }
