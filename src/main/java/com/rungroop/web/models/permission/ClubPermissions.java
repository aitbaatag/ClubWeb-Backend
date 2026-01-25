package com.rungroop.web.models.permission;


import com.rungroop.web.models.Club;
import com.rungroop.web.models.User;
import jakarta.persistence.*;

@Entity
@Table(name = "club_permissions")
public class ClubPermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Club club;

    @Enumerated(EnumType.STRING)
    private Permission permission;
}