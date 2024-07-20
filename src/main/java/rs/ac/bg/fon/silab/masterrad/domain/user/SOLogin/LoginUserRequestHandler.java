package rs.ac.bg.fon.silab.masterrad.domain.user.SOLogin;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.config.JwtService;
import rs.ac.bg.fon.silab.masterrad.dto.user.AuthenticationRequest;
import rs.ac.bg.fon.silab.masterrad.dto.user.AuthenticationResponse;
import rs.ac.bg.fon.silab.masterrad.exception.UserNotVerifiedException;
import rs.ac.bg.fon.silab.masterrad.repository.UserRepository;


@Component
@RequiredArgsConstructor
public class LoginUserRequestHandler
        implements RequestHandler<AuthenticationRequest,
        AuthenticationResponse> {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final Mediator mediator;
    private AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );
        val user = repository.findByUsername(request.username())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(!user.isVerified()){
            throw new UserNotVerifiedException("Please verify your account via email to proceed.");
        }

        val jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }
    @Override
    public AuthenticationResponse handle(@NotNull AuthenticationRequest
                                                     authenticationRequest) {

       val token = authenticate(authenticationRequest);
       this.mediator.emit(token);

       return token;
    }
}
