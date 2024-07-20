package rs.ac.bg.fon.silab.masterrad.dto.user;

import io.jkratz.mediator.core.Event;

public record AuthenticationResponse (
        String token
) implements Event
{ }
