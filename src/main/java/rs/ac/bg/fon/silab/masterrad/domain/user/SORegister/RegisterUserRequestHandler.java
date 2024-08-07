package rs.ac.bg.fon.silab.masterrad.domain.user.SORegister;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.config.JwtService;
import rs.ac.bg.fon.silab.masterrad.domain.user.UserUtil;
import rs.ac.bg.fon.silab.masterrad.dto.user.AuthenticationResponse;
import rs.ac.bg.fon.silab.masterrad.dto.user.RegisterRequest;
import rs.ac.bg.fon.silab.masterrad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.masterrad.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class RegisterUserRequestHandler implements RequestHandler<RegisterRequest, AuthenticationResponse> {
    private final UserRepository repository;
    private final Mediator mediator;
    private final RegisterRequestUserMapper registerRequestUserMapper;
    private final JwtService jwtService;
    private final UserUtil util;

    private AuthenticationResponse register(RegisterRequest request) {

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
        AuthenticationResponse token =  register(registerRequest);

        this.mediator.emit(token);

        return token;
    }
}
