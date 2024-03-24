package rs.ac.bg.fon.silab.diplomskirad.userUtils;

import io.jkratz.mediator.core.Request;

public record TokenValidationRequest(
        String username,
        String password
) implements Request<TokenValidationResponse> {
}
