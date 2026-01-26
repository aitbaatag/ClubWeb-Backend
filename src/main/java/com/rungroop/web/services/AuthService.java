package com.rungroop.web.services;

import com.rungroop.web.dto.LoginDto;
import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.dto.UserDto;
import com.rungroop.web.models.User;

public interface AuthService {
    boolean Signup(RegistrationDto registrationDto);
    public User authenticate(LoginDto loginDto);
    UserDto getUserById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
