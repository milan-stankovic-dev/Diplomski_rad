package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOLogin;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;

@Component
@Log
public class UserLoginEventHandler implements EventHandler<AuthenticationResponse> {

    @Override
    public void handle(@NotNull AuthenticationResponse authenticationResponse) {
        log.info("User logged in.");
    }
}
