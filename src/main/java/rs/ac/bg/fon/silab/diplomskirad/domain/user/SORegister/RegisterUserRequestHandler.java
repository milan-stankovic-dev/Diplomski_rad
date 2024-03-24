package rs.ac.bg.fon.silab.diplomskirad.domain.user.SORegister;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
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
    private final EmailService emailService;

    public AuthenticationResponse register(RegisterRequest request)
            throws UserAlreadyRegisteredException {

        val user = registerRequestUserMapper.dTOtoEntity(request);

        final Optional<User> dbUserByUsernameOptional =
                repository.findByUsername(request.username());

        if(dbUserByUsernameOptional.isPresent()){
            throw new UserAlreadyRegisteredException("This user already exists." +
                    " Pick a new username.");
        }

        final Optional<User> dbUserByEmailOptional =
                repository.findByEmail(request.email());

        if(dbUserByEmailOptional.isPresent()){
            throw new UserAlreadyRegisteredException("Email already used. Please pick a new email or " +
                    "login with this email.");
        }

        final String jwtToken = sendEmailToUser(user);
        repository.save(user);

        return new AuthenticationResponse(jwtToken);
    }

    public String sendEmailToUser(User user){
        final String jwtToken = jwtService.generateToken(user);
        final String verificationLink = "http://localhost:4200/verify-email?token=" + jwtToken;
        final String emailBody = "Thank you for registering to our service! " +
                "One more step is required. Please follow the link to properly " +
                "sign up to our service.\n\n\n" + verificationLink;
        emailService.sendEmail(user.getEmail(), "Email verification", emailBody);

        return jwtToken;
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
