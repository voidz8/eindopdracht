package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.payload.request.LoginRequest;
import nl.novi.eindopdracht.payload.request.SignupRequest;
import nl.novi.eindopdracht.payload.response.JwtResponse;
import nl.novi.eindopdracht.payload.response.MessageResponse;
import nl.novi.eindopdracht.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        return authorizationService.authenticateUser(loginRequest);
    }

    @PostMapping("signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signupRequest){
        return authorizationService.registerUser(signupRequest);
    }
}
