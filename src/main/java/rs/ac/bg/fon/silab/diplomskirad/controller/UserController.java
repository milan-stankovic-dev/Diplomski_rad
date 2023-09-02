package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.domain.User;
import rs.ac.bg.fon.silab.diplomskirad.exception.UserAlreadyRegisteredException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.AuthRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.RegisterRequestUserMapper;
import rs.ac.bg.fon.silab.diplomskirad.service.UserService;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationRequest;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.AuthenticationResponse;
import rs.ac.bg.fon.silab.diplomskirad.userUtils.RegisterRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService service;
    private final RegisterRequestUserMapper registerUserMapper;
    private final AuthRequestUserMapper authUserMapper;
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
    public ResponseEntity<Object> loginUser(
            @RequestBody AuthenticationRequest request){
        try{
            return ResponseEntity.ok(service.authenticate(request));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/verify-token/{token}")
    public ResponseEntity<Boolean> verifyToken(
            @PathVariable String token,
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.verifyToken(token,request));
    }

    @PostMapping("/resend-email")
    public ResponseEntity<Void> resendEmail(
            @RequestBody RegisterRequest request
    ){
        service.sendEmailToUser(registerUserMapper.dTOtoEntity(request));
        return ResponseEntity.ok().build();
    }
}
