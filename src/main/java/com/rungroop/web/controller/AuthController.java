package com.rungroop.web.controller;


import com.rungroop.web.dto.LoginDto;
import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.User;
import com.rungroop.web.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationDto userRegistrationDto) {
        boolean isCreated = authService.Signup(userRegistrationDto);

        if (isCreated) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create user. Please try again.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.authenticate(loginDto);
        System.out.println(loginDto);
        return ResponseEntity.ok("User logged in successfully");
    }
}
