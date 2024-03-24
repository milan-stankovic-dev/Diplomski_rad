package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOResendEmail;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
import rs.ac.bg.fon.silab.diplomskirad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.service.EmailService;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationResponse;

@Component
@RequiredArgsConstructor
public class ResendEmailCommandHandler
        implements CommandHandler<RegisterRequest> {

    private final Mediator mediator;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final RegisterRequestUserMapper mapper;

    public void sendEmailToUser(User user){
        final String jwtToken = jwtService.generateToken(user);
        final String verificationLink = "http://localhost:4200/verify-email?token=" + jwtToken;
        final String emailBody = "Thank you for registering to our service! " +
                "One more step is required. Please follow the link to properly " +
                "sign up to our service.\n\n\n" + verificationLink;
        emailService.sendEmail(user.getEmail(), "Email verification", emailBody);
    }
    @Override
    public void handle(@NotNull RegisterRequest registerRequest) {
        final User userToSendEmailTo = mapper.dTOtoEntity(registerRequest);

        sendEmailToUser(userToSendEmailTo);
        this.mediator.emit(new
                TokenValidationResponse(true));
    }


}
