package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.config.JwtService;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
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
    public AuthenticationResponse register(RegisterRequest request)
            throws UserAlreadyRegisteredException{

        var user = registerRequestUserMapper.dTOtoEntity(request);

        var dbUser = repository.findByUsername(request.username());
        if(dbUser.isPresent()){
            throw new UserAlreadyRegisteredException("This user already exists." +
                    " Pick a new name or login.");
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );
        var user = repository.findByUsername(request.username())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }
}
