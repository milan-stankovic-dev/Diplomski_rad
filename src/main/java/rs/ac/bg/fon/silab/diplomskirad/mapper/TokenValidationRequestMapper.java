package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.TokenValidationResponse;

@Component
public non-sealed class TokenValidationRequestMapper implements DtoDomainMapper<TokenValidationRequest, User> {
    @Override
    public TokenValidationRequest entityToDTO(User user) {
        if (user == null) {
            return null;
        }

        return new TokenValidationRequest(user.getUsername(), user.getPassword());
    }

    @Override
    public User dTOtoEntity(TokenValidationRequest tokenValidationRequest) {
        if(tokenValidationRequest == null) {
            return null;
        }

        return User.builder()
                .username(tokenValidationRequest.username())
                .password(tokenValidationRequest.password())
                .build();
    }
}
