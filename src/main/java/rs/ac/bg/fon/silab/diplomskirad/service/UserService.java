package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.domain.User;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserNotVerifiedException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.AuthRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final RegisterRequestUserMapper registerRequestUserMapper;
    private final AuthRequestUserMapper authRequestUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthRequestUserMapper userMapper;
    public AuthenticationResponse register(RegisterRequest request)
            throws UserAlreadyRegisteredException{

        var user = registerRequestUserMapper.dTOtoEntity(request);

        var dbUserByUsernameOptional = repository.findByUsername(request.username());
        if(dbUserByUsernameOptional.isPresent()){
            throw new UserAlreadyRegisteredException("This user already exists." +
                    " Pick a new username.");
        }

        var dbUserByEmailOptional = repository.findByEmail(request.email());
        if(dbUserByEmailOptional.isPresent()){
            throw new UserAlreadyRegisteredException("Email already used. Please pick a new email or " +
                    "login with this email.");
        }

        var jwtToken = sendEmailToUser(user);
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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );
        var user = repository.findByUsername(request.username())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(!user.isVerified()){
            throw new UserNotVerifiedException("Please verify your account via email to proceed.");
        }

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public boolean verifyToken(String token, AuthenticationRequest request) {
        try{
            var user = userMapper.dTOtoEntity(request);
            if (jwtService.isTokenValid(token, user)) {
                var userFromDB = repository.findByUsername(user.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                userFromDB.setVerified(true);
                repository.save(userFromDB);

                return true;

            } else {
                return false;
            }
        }catch (Exception ex){
            return false;
        }
    }
}
