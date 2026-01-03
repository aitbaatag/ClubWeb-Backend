package com.rungroop.web.services.impl;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.dto.UserDto;
import com.rungroop.web.models.Role;
import com.rungroop.web.models.User;
import com.rungroop.web.repository.RoleRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean SaveUser(RegistrationDto registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()) != null) {
            return false;
        }

        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            return false;
        }

        Role userRole = roleRepository.findByName("USER");

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
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
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
