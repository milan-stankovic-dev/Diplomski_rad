package rs.ac.bg.fon.silab.masterrad.domain.user;

import io.jkratz.mediator.core.Command;
import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.Request;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.masterrad.dto.user.AuthenticationRequest;
import rs.ac.bg.fon.silab.masterrad.dto.user.RegisterRequest;
import rs.ac.bg.fon.silab.masterrad.dto.user.TokenVerificationRequest;
import rs.ac.bg.fon.silab.masterrad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.masterrad.mapper.TokenValidationWithTokenMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final RegisterRequestUserMapper registerUserMapper;
    private final Mediator mediator;
    private final TokenValidationWithTokenMapper tokenMapper;
    @PostMapping("/register")
    public ResponseEntity<Object> registerUserNew(
            @RequestBody RegisterRequest request){
        val token = this.mediator.dispatch((Request<?>) request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(
            @RequestBody AuthenticationRequest request){
        val token = this.mediator.dispatch(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify-token/{token}")
    public ResponseEntity<Boolean> verifyToken(
            @PathVariable String token,
            @RequestBody TokenVerificationRequest request) {
        val result = this.mediator.dispatch(
                tokenMapper.addToken(request, token));

        return ResponseEntity.ok(result.verified());
    }

    @PostMapping("/resend-email")
    public ResponseEntity<Void> resendEmail(
            @RequestBody RegisterRequest request
    ){
        this.mediator.dispatch((Command) request);
        return ResponseEntity.ok().build();
    }
}
