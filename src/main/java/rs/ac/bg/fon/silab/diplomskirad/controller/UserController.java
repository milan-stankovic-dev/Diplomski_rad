package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.User;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.service.UserService;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(
            @RequestBody RegisterRequest request){
       try{
           return ResponseEntity.ok(service.register(request));
       }
       catch (UserAlreadyRegisteredException ex){
           return ResponseEntity.status(HttpStatus.FORBIDDEN)
                   .body(ex.getMessage());
       }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
