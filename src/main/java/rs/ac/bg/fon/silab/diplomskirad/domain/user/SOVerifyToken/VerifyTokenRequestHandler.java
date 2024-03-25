package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOVerifyToken;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.mapper.TokenValidationRequestMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationResponse;

@Component
@RequiredArgsConstructor
public class VerifyTokenRequestHandler
        implements RequestHandler<TokenValidationRequest,
                                  TokenValidationResponse> {
    private final Mediator mediator;
    private final TokenValidationRequestMapper userMapper;
    private final JwtService jwtService;
    private final UserRepository repository;

    @Setter
    private String tokenToVerify;

    private void setUserToVerifiedByUsername(String username) {
        val userFromDB = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userFromDB.setVerified(true);
        repository.save(userFromDB);
    }

    @Override
    public TokenValidationResponse handle(@NotNull TokenValidationRequest
                                                      tokenValidationRequest) {

        val userFromRequest = userMapper.dTOtoEntity(tokenValidationRequest);
        final boolean valid = jwtService.isTokenValid(this.tokenToVerify,
                userFromRequest);

        if(valid)
            setUserToVerifiedByUsername(userFromRequest.getUsername());

        val response = new TokenValidationResponse(valid);
        this.mediator.emit(response);
        return response;
    }
}

