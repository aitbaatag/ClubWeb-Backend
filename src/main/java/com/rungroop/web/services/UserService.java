package com.rungroop.web.services;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.dto.UserDto;

public interface UserService {
    boolean SaveUser(RegistrationDto registrationDto);
    UserDto getUserById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
