package com.rungroop.web.services;

import com.rungroop.web.models.User;

public interface ClubMemberService {
    boolean joinClub(Long clubId, User user);
    boolean leaveClub(Long clubId, User user);
    boolean isMember(Long clubId, Long userId);
    int getMemberCount(Long clubId);
}

