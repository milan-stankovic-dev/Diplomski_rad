package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
import rs.ac.bg.fon.silab.diplomskirad.dto.user.TokenVerificationRequest;

@Component
public non-sealed class TokenValidationRequestMapper implements DtoDomainMapper<TokenVerificationRequest, User> {
    @Override
    public TokenVerificationRequest entityToDTO(User user) {
        if (user == null) {
            return null;
        }

        return new TokenVerificationRequest(user.getUsername(), user.getPassword());
    }

    @Override
    public User dTOtoEntity(TokenVerificationRequest tokenVerificationRequest) {
        if(tokenVerificationRequest == null) {
            return null;
        }

        return User.builder()
                .username(tokenVerificationRequest.username())
                .password(tokenVerificationRequest.password())
                .build();
    }
}
