package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.Role;
import rs.ac.bg.fon.silab.diplomskirad.domain.User;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

import java.sql.Ref;

@Component
@RequiredArgsConstructor
public non-sealed class RegisterRequestUserMapper implements DtoDomainMapper<RegisterRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterRequest entityToDTO(User user) {
        var registerRequest = new RegisterRequest(
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );

        return registerRequest;
    }

    @Override
    public User dTOtoEntity(RegisterRequest registerRequest) {
        var user = new User();
        user.setName(registerRequest.firstname());
        user.setLastname(registerRequest.lastname());
        user.setUsername(registerRequest.username());
        user.setPassword(
                passwordEncoder.encode(registerRequest.password())
        );
        user.setEmail(registerRequest.email());
        user.setRole(Role.USER);

        return user;
    }
}
