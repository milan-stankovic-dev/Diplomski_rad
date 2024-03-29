package rs.ac.bg.fon.silab.diplomskirad.dto.user;

import io.jkratz.mediator.core.Request;

public record AuthenticationRequest(
        String username,
        String password
) implements Request<AuthenticationResponse>
{ }
