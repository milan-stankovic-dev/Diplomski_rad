package rs.ac.bg.fon.silab.masterrad.domain.user.SOVerifyToken;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.config.JwtService;
import rs.ac.bg.fon.silab.masterrad.domain.TokenVerificationRequestWithToken;
import rs.ac.bg.fon.silab.masterrad.dto.user.TokenVerificationResponse;
import rs.ac.bg.fon.silab.masterrad.mapper.TokenValidationRequestMapper;
import rs.ac.bg.fon.silab.masterrad.mapper.TokenValidationWithTokenMapper;
import rs.ac.bg.fon.silab.masterrad.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class VerifyTokenRequestHandler
        implements RequestHandler<TokenVerificationRequestWithToken,
                TokenVerificationResponse> {

    private final Mediator mediator;
    private final TokenValidationRequestMapper userMapper;
    private final JwtService jwtService;
    private final UserRepository repository;
    private final TokenValidationWithTokenMapper tokenMapper;

    private void setUserToVerifiedByUsername(String username) {
        val userFromDB = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userFromDB.setVerified(true);
        repository.save(userFromDB);
    }

    @Override
    public TokenVerificationResponse handle(@NotNull TokenVerificationRequestWithToken
                                                        tokenVerificationRequest) {

        val userFromRequest = userMapper.dTOtoEntity(
                tokenMapper.removeToken(tokenVerificationRequest));

        final boolean valid = jwtService.isTokenValid(tokenVerificationRequest.token(),
                userFromRequest);

        if(valid)
            setUserToVerifiedByUsername(userFromRequest.getUsername());

        val response = new TokenVerificationResponse(valid);
        this.mediator.emit(response);
        return response;
    }
}

