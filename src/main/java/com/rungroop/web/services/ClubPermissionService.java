package com.rungroop.web.services;

public interface ClubPermissionService {
    boolean canCreateEvent(Long clubId, Long userId);
}
