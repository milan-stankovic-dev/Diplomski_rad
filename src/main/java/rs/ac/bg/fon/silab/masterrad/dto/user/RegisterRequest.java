package rs.ac.bg.fon.silab.masterrad.dto.user;

import io.jkratz.mediator.core.Command;
import io.jkratz.mediator.core.Request;

public record RegisterRequest(
         String firstname,
         String lastname,
         String username,
         String password,
         String email
) implements Request<AuthenticationResponse>, Command { }
