package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.enumeration.Role;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.User;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationRequest;

@Component
@RequiredArgsConstructor
public non-sealed class AuthRequestUserMapper
        implements DtoDomainMapper<AuthenticationRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationRequest entityToDTO(User user) {
        var authRequest = new AuthenticationRequest(
                user.getUsername(),
                user.getPassword()
        );

        return authRequest;
    }

    @Override
    public User dTOtoEntity(AuthenticationRequest authenticationRequest) {
        var user = new User();
        user.setUsername(authenticationRequest.username());
        user.setPassword(
            passwordEncoder.encode(authenticationRequest.password())
        );
        user.setRole(Role.USER);

        return user;
    }
}
