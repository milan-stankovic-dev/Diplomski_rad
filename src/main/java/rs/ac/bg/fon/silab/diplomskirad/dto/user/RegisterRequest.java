package rs.ac.bg.fon.silab.diplomskirad.dto.user;

import io.jkratz.mediator.core.Command;
import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.dto.user.AuthenticationResponse;

public record RegisterRequest(
         String firstname,
         String lastname,
         String username,
         String password,
         String email
) implements Request<AuthenticationResponse>, Command { }
