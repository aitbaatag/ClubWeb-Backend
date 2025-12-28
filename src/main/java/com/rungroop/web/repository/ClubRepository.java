package com.rungroop.web.repository;


import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String title);

    @Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :qury, '%')")
    List<Club> searchClubs(String qury);
}
