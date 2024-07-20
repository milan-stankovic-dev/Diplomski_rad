package rs.ac.bg.fon.silab.masterrad.domain.user;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.dto.user.TokenVerificationResponse;

public record VerifyTokenRequest
        () implements Request<TokenVerificationResponse> { }
