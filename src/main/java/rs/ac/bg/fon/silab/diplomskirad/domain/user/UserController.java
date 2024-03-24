package rs.ac.bg.fon.silab.diplomskirad.domain.user;

import io.jkratz.mediator.core.Command;
import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.Request;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.bg.fon.silab.diplomskirad.domain.user.SOVerifyToken.VerifyTokenRequestHandler;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.AuthRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.service.UserService;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final RegisterRequestUserMapper registerUserMapper;
    private final Mediator mediator;
    private final VerifyTokenRequestHandler tokenRequestHandler;

//    @PostMapping("/registerOLD")
//    public ResponseEntity<Object> registerUser(
//            @RequestBody RegisterRequest request){
//       try{
//           return ResponseEntity.ok(service.register(request));
//       }
//       catch (UserAlreadyRegisteredException ex){
//           return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                   .body(ex.getMessage());
//       }
//    }

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
            @RequestBody TokenValidationRequest request) {

        tokenRequestHandler.setTokenToValidate(token);
        val result = this.mediator.dispatch(request);

        return ResponseEntity.ok(result.valid());
    }

//    @PostMapping("/resend-emailOLD")
//    public ResponseEntity<Void> resendEmailOld(
//            @RequestBody RegisterRequest request
//    ){
//        service.sendEmailToUser(registerUserMapper.dTOtoEntity(request));
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/resend-email")
    public ResponseEntity<Void> resendEmail(
            @RequestBody RegisterRequest request
    ){
        this.mediator.dispatch((Command) request);
        return ResponseEntity.ok().build();
    }
}
