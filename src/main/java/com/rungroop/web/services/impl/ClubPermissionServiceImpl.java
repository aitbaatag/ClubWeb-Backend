package com.rungroop.web.services.impl;

import com.rungroop.web.models.permission.Permission;
import com.rungroop.web.repository.ClubPermissionRepository;
import com.rungroop.web.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClubPermissionServiceImpl {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ClubPermissionRepository permissionRepository;

    public boolean canCreateEvent(Long clubId, Long userId) {
        if (isOwner(clubId, userId)) return true;

        return permissionRepository.existsByUser_IdAndClub_IdAndPermission(userId, clubId, Permission.EVENT_CREATE);
    }

    private boolean isOwner(Long clubId, Long userId) {
        return clubRepository.existsByIdAndOwner_Id(clubId, userId);
    }
}
