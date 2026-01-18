package com.rungroop.web.controller;


import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.dto.UserDto;
import com.rungroop.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> listUsers(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }
}
