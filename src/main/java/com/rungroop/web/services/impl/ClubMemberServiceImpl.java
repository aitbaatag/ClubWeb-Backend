package com.rungroop.web.services.impl;

import com.rungroop.web.models.Club;
import com.rungroop.web.models.ClubMember;
import com.rungroop.web.models.User;
import com.rungroop.web.repository.ClubMemberRepository;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.services.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClubMemberServiceImpl implements ClubMemberService {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    @Transactional
    public boolean joinClub(Long clubId, User user) {
        // Check if club exists
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + clubId));

        // Check if user is already a member
        if (clubMemberRepository.existsByClubIdAndUserId(clubId, user.getId())) {
            return false; // User is already a member
        }

        // Create new membership
        ClubMember clubMember = new ClubMember();
        clubMember.setClub(club);
        clubMember.setUser(user);

        clubMemberRepository.save(clubMember);
        return true;
    }

    @Override
    @Transactional
    public boolean leaveClub(Long clubId, User user) {
        // Find the membership
        ClubMember membership = clubMemberRepository.findByClubIdAndUserId(clubId, user.getId())
                .orElse(null);

        if (membership == null) {
            return false; // User is not a member
        }

        clubMemberRepository.delete(membership);
        return true;
    }

    @Override
    public boolean isMember(Long clubId, Long userId) {
        return clubMemberRepository.existsByClubIdAndUserId(clubId, userId);
    }

    @Override
    public int getMemberCount(Long clubId) {
        return clubMemberRepository.countByClubId(clubId);
    }
}

