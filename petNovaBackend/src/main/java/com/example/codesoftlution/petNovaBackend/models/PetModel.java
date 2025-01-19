package com.example.codesoftlution.petNovaBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class PetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String race;
    private Integer age;
    private String color;
    private boolean active;

    @Column(columnDefinition = "TEXT")
    private String obsevations;


    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private SpecieModel specie;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
