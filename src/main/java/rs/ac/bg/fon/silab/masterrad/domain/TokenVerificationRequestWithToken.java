package rs.ac.bg.fon.silab.masterrad.domain;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.dto.user.TokenVerificationResponse;

public record TokenVerificationRequestWithToken(
                String username,
                String password,
                String token
) implements Request<TokenVerificationResponse> { }
