package com.rungroop.web.repository;

import com.rungroop.web.models.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    // Method to check if a user is already a member of a club
    boolean existsByClubIdAndUserId(Long clubId, Long userId);

    // Find membership by club and user
    Optional<ClubMember> findByClubIdAndUserId(Long clubId, Long userId);

    // Count members in a club
    int countByClubId(Long clubId);
}
