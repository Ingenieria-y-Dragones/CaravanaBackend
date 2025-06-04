package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.JwtAuthenticationResponse;
import co.edu.javeriana.caravana.dto.LoginDTO;
import co.edu.javeriana.caravana.dto.UserRegistrationDTO;
import co.edu.javeriana.caravana.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody UserRegistrationDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
