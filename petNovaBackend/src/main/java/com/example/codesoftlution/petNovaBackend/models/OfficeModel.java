package com.example.codesoftlution.petNovaBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offices")
public class OfficeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String linkLogoPhoto;

    private boolean active;

    //Veterinario Asociado al consultorio
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private UserModel veterinarioCreador;
}
