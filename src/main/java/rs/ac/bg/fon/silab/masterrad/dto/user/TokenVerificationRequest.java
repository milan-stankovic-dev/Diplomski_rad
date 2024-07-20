package rs.ac.bg.fon.silab.masterrad.dto.user;

import io.jkratz.mediator.core.Request;

public record TokenVerificationRequest(
        String username,
        String password
) implements Request<TokenVerificationResponse> {
}
