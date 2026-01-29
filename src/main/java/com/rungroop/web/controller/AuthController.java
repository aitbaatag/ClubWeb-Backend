package com.rungroop.web.controller;


import com.rungroop.web.dto.AuthResponseDto;
import com.rungroop.web.dto.LoginDto;
import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.User;
import com.rungroop.web.security.jwt.JwtUtils;
import com.rungroop.web.security.userdetails.CustomUserDetails;
import com.rungroop.web.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthService authService, JwtUtils jwtUtils) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
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
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.authenticate(loginDto);
        CustomUserDetails customUserDetails = new CustomUserDetails(authenticatedUser);
        String jwtToken = jwtUtils.generateToken(customUserDetails);

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtUtils.getExpirationTime())
                .userId(authenticatedUser.getId())
                .username(authenticatedUser.getUsername())
                .build();

        return ResponseEntity.ok(authResponseDto);
    }
}
