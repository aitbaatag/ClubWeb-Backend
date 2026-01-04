package com.rungroop.web.controller;


import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationDto userRegistrationDto) {
        boolean isCreated = userService.SaveUser(userRegistrationDto);

        if (isCreated) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create user. Please try again.");
        }
    }
}
