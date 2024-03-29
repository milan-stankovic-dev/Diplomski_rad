package rs.ac.bg.fon.silab.diplomskirad.domain;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenVerificationResponse;

public record TokenVerificationRequestWithToken(
                String username,
                String password,
                String token
) implements Request<TokenVerificationResponse> { }
