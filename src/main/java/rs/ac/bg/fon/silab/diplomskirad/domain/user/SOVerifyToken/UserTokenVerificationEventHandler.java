package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOVerifyToken;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.dto.user.TokenVerificationResponse;

@Component
@Log
public class UserTokenVerificationEventHandler implements
        EventHandler<TokenVerificationResponse> {
    @Override
    public void handle(@NotNull TokenVerificationResponse tokenVerificationResponse) {
        log.info("TOKEN VALIDATION RESPONSE: Token verified? " +
                tokenVerificationResponse.verified());
    }
}
