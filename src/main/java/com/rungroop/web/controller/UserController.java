package com.rungroop.web.controller;


import com.rungroop.web.dto.UserDto;
import com.rungroop.web.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> listUsers(@PathVariable("id") Long id) {
        UserDto userDto = authService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }
}
