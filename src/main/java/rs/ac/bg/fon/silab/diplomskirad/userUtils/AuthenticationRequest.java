package rs.ac.bg.fon.silab.diplomskirad.userUtils;

import io.jkratz.mediator.core.Request;

public record AuthenticationRequest(
        String username,
        String password
) implements Request<AuthenticationResponse>
{ }
