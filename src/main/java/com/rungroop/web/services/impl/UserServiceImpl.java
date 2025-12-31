package com.rungroop.web.services.impl;

import com.rungroop.web.models.Role;
import com.rungroop.web.models.User;
import com.rungroop.web.repository.RoleRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.services.UserService;
import com.rungroop.web.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void SaveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());

        Role userRole = roleRepository.findByName("USER");
        user.getRoles().add(userRole);
        // Role adminRole = roleRepository.findByName("ADMIN");
        // user.getRoles().add(adminRole);

        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Find the role
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }

        // Add the role to the user's roles
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    public void removeRoleFromUser(Long userId, String roleName) {
        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Find the role
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }

        // Remove the role from the user's roles
        user.getRoles().remove(role);

        userRepository.save(user);

    }
}
