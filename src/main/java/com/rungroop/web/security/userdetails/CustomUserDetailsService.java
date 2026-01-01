package com.rungroop.web.security.userdetails;

import com.rungroop.web.models.User;
import com.rungroop.web.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        User user = userRepository.findByUsername(username);
        return new CustomUserDetails(user);
    }
}
