package rs.ac.bg.fon.silab.diplomskirad.domain.user.SOVerifyToken;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.mapper.AuthRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.TokenValidationRequestMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationRequest;
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
    private String tokenToValidate;
    public boolean verifyToken(String token, TokenValidationRequest request) {
        try{
            val user = userMapper.dTOtoEntity(request);
            if (jwtService.isTokenValid(token, user)) {
                val userFromDB = repository.findByUsername(user.getUsername())
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
    @Override
    public TokenValidationResponse handle(@NotNull TokenValidationRequest
                                                      tokenValidationRequest) {

        final Boolean valid = verifyToken(null, tokenValidationRequest);
        val response = new TokenValidationResponse(valid);
        this.mediator.emit(response);

        return response;
    }
}

