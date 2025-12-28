package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.services.ClubService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDto> listClubs() {
        return  clubService.findAllClubs();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClubDto> getClubById(@PathVariable("id") Long id) {
        ClubDto club = clubService.findById(id);
        return new ResponseEntity<>(club, HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<ClubDto> createClub(@Valid @RequestBody ClubDto clubDto) {
        ClubDto savedClub = clubService.createClub(clubDto);
        return new ResponseEntity<>(savedClub, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<ClubDto> searchClubs(@RequestParam("query") String qury) {
        List<ClubDto> clubs = clubService.searchClubs(qury);
        return clubs;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteClub(@PathVariable("id") Long id) {
        clubService.deleteClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubDto> updateClub(@PathVariable("id") Long id, @Valid @RequestBody ClubDto clubDto) {
        clubDto.setId(id);
        ClubDto updatedClub = clubService.updateClub(clubDto);
        return new ResponseEntity<>(updatedClub, HttpStatus.OK);
    }

}
