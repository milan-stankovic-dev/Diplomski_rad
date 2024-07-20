package rs.ac.bg.fon.silab.masterrad.domain.user.SOResendEmail;

import io.jkratz.mediator.core.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.config.JwtService;
import rs.ac.bg.fon.silab.masterrad.domain.user.User;
import rs.ac.bg.fon.silab.masterrad.domain.user.UserUtil;
import rs.ac.bg.fon.silab.masterrad.dto.user.RegisterRequest;
import rs.ac.bg.fon.silab.masterrad.mapper.RegisterRequestUserMapper;

@Component
@RequiredArgsConstructor
public class ResendEmailCommandHandler
        implements CommandHandler<RegisterRequest> {

    private final JwtService jwtService;
    private final RegisterRequestUserMapper mapper;
    private final UserUtil util;

    @Override
    public void handle(@NotNull RegisterRequest registerRequest) {
        final User userToSendEmailTo = mapper.dTOtoEntity(registerRequest);
        final String token = jwtService.generateToken(userToSendEmailTo);

        util.sendEmailTo(userToSendEmailTo, token);
    }

}
