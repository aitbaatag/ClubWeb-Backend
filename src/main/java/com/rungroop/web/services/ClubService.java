package com.rungroop.web.services;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClubService  {
    List<ClubDto> findAllClubs();
    ClubDto createClub(ClubDto clubDto, User currentUser);
    ClubDto updateClub(ClubDto clubDto);
    ClubDto findById(Long id);

    void deleteClub(Long id);

    List<ClubDto> searchClubs(String qury);
}
