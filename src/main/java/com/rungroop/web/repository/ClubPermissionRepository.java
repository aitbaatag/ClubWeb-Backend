package com.rungroop.web.repository;

import com.rungroop.web.models.permission.ClubPermissions;
import com.rungroop.web.models.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubPermissionRepository extends JpaRepository<ClubPermissions, Long> {

    boolean existsByUser_IdAndClub_IdAndPermission(Long userId, Long clubId, Permission permission);
}
