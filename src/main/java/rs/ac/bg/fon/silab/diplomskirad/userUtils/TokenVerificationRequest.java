package rs.ac.bg.fon.silab.diplomskirad.userUtils;

import io.jkratz.mediator.core.Request;

public record TokenVerificationRequest(
        String username,
        String password
) implements Request<TokenVerificationResponse> {
}
