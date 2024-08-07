package rs.ac.bg.fon.silab.masterrad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.TokenVerificationRequestWithToken;
import rs.ac.bg.fon.silab.masterrad.dto.user.TokenVerificationRequest;

@Component
public class TokenValidationWithTokenMapper {
    public TokenVerificationRequestWithToken addToken(TokenVerificationRequest request,
                                                      String token) {
        return request == null? null: new TokenVerificationRequestWithToken(
            request.username(), request.password(), token
        );
    }

    public TokenVerificationRequest removeToken(TokenVerificationRequestWithToken
                                                        request ){
        return request == null ? null : new TokenVerificationRequest(
                request.username(), request.password()
        );
    }
}
