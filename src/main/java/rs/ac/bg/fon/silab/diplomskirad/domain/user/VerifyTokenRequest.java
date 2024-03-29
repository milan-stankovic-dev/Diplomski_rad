package rs.ac.bg.fon.silab.diplomskirad.domain.user;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenVerificationResponse;

public record VerifyTokenRequest
        () implements Request<TokenVerificationResponse> { }
