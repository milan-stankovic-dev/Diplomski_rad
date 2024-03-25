package rs.ac.bg.fon.silab.diplomskirad.domain.user.SORegister;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.UserUtil;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;
import rs.ac.bg.fon.silab.diplomskirad.service.EmailService;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisterUserRequestHandler implements RequestHandler<RegisterRequest, AuthenticationResponse> {
    private final UserRepository repository;
    private final Mediator mediator;
    private final RegisterRequestUserMapper registerRequestUserMapper;
    private final JwtService jwtService;
    private final UserUtil util;

    private AuthenticationResponse register(RegisterRequest request)
            throws UserAlreadyRegisteredException {

        val user = registerRequestUserMapper.dTOtoEntity(request);

        util.validateUsernameNotTaken(request.username());
        util.validateEmailNotExisting(request.email());

        final String jwtToken = jwtService.generateToken(user);
        util.sendEmailTo(user, jwtToken);

        repository.save(user);

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse handle(@NotNull RegisterRequest registerRequest) {
        AuthenticationResponse token = null;
        try {
            token = register(registerRequest);
        } catch (UserAlreadyRegisteredException e) {
            throw new RuntimeException(e);
        }
        this.mediator.emit(token);

        return token;
    }
}
