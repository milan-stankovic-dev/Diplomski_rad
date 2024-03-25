package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOVerifyToken;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationResponse;

@Component
@Log
public class UserTokenVerificationEventHandler implements
        EventHandler<TokenValidationResponse> {
    @Override
    public void handle(@NotNull TokenValidationResponse tokenValidationResponse) {
        log.info("TOKEN VALIDATION RESPONSE: Token valid? " +
                tokenValidationResponse.valid());
    }
}
