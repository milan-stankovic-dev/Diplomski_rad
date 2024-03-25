package rs.ac.bg.fon.silab.diplomskirad.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;
import rs.ac.bg.fon.silab.diplomskirad.service.EmailService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository repository;
    private final EmailService emailService;

    public void validateUsernameNotTaken(String username)
            throws UserAlreadyRegisteredException {

        final Optional<User> dbUserByUsernameOptional =
                repository.findByUsername(username);

        if(dbUserByUsernameOptional.isPresent()){
            throw new UserAlreadyRegisteredException("This user already exists." +
                    " Pick a new username.");
        }
    }

    public void validateEmailNotExisting(String email)
            throws UserAlreadyRegisteredException {

        final Optional<User> dbUserByEmailOptional =
                repository.findByEmail(email);

        if(dbUserByEmailOptional.isPresent()){
            throw new UserAlreadyRegisteredException("Email already used. " +
                    "Please pick a new email or " +
                    "login with this email.");
        }
    }

    public void sendEmailTo(User user, String jwtToken) {
        final String verificationLink = "http://localhost:4200/verify-email?token=" + jwtToken;
        final String emailBody = "Thank you for registering to our service! " +
                "One more step is required. Please follow the link to properly " +
                "sign up to our service.\n\n\n" + verificationLink;
        emailService.sendEmail(user.getEmail(), "Email verification", emailBody);
    }

}
