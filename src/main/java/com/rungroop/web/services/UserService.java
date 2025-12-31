package com.rungroop.web.services;

import com.rungroop.web.dto.RegistrationDto;

public interface UserService {
    void SaveUser(RegistrationDto registrationDto);
    void addRoleToUser(Long userId, String roleName);
    void removeRoleFromUser(Long userId, String roleName);
}
