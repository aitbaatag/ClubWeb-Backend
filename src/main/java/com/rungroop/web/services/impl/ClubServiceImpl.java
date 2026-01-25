package com.rungroop.web.services.impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.User;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.security.userdetails.CustomUserDetails;
import com.rungroop.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl  implements ClubService {

    @Autowired
    private final ClubRepository clubRepository;

    @Autowired
    private final UserRepository userRepository;

    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs()
    {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(this::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public ClubDto findById(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        return mapToClubDto(club);
    }

    @Override
    public void deleteClub(Long id) {

        clubRepository.deleteById(id);
    }

    @Override
    public ClubDto createClub(ClubDto clubDto, User currentUser) {

        Club club = mapToClub(clubDto);
        club.setOwner(currentUser);
        Club saved = clubRepository.save(club);
        return mapToClubDto(saved);
    }

    @Override
    public List<ClubDto> searchClubs(String qury) {
        List<Club> clubs = clubRepository.searchClubs(qury);
        return clubs.stream().map(this::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public ClubDto updateClub(ClubDto clubDto) {
        if (clubDto.getId() == null) {
            throw new IllegalArgumentException("Club ID cannot be null for update operation");
        }
        Club existingClub = clubRepository.findById(clubDto.getId())
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + clubDto.getId()));


        existingClub.setTitle(clubDto.getTitle());
        existingClub.setPhotoUrl(clubDto.getPhotoUrl());
        existingClub.setContent(clubDto.getContent());

        Club updatedClub = clubRepository.save(existingClub);
        return mapToClubDto(updatedClub);
    }

    private Club mapToClub(ClubDto clubDto) {
        return Club.builder()
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .build();
    }
    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .ownerId(club.getOwner() != null ? club.getOwner().getId() : null)
                .ownerUsername(club.getOwner() != null ? club.getOwner().getUsername() : null)
                .build();
    }
}
