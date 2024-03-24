package rs.ac.bg.fon.silab.diplomskirad.domain.user.SORegister;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

import java.time.LocalTime;

@Component
@Log
public class UserRegistrationEventHandler implements EventHandler<AuthenticationResponse> {
    @Override
    public void handle(@NotNull AuthenticationResponse authenticationResponse) {
        log.info("New user registration completed! Time: " + LocalTime.now());
    }
}
