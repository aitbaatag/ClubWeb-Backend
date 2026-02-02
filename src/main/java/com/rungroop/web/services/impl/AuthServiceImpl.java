package com.rungroop.web.services.impl;

import com.rungroop.web.dto.LoginDto;
import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.dto.UserDto;
import com.rungroop.web.models.Role;
import com.rungroop.web.models.User;
import com.rungroop.web.repository.RoleRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean Signup(RegistrationDto registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            return false;
        }

        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            return false;
        }

        Role userRole = roleRepository.findByName("USER");

        if (userRole == null) {
            throw new RuntimeException("USER role not found in database");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.getRoles().add(userRole);

        userRepository.save(user);
        return true;
    }

    @Override
    public User authenticate(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword())
        );
        return userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserDto getUserById(Long Id)
    {
        User user = userRepository.findById(Id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .FirstName(user.getFirstName())
                .LastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }
}
