package com.example.codesoftlution.petNovaBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String idNumber;
    private String username;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolModel role;
    private String phoneNumber;
    private boolean active;

    @Column(columnDefinition = "TEXT")
    private String token;

    @Column(columnDefinition = "TEXT")
    private String firebaseToken;

    private LocalDateTime creationDate;
    private String linkPerfilPhoto;

}
