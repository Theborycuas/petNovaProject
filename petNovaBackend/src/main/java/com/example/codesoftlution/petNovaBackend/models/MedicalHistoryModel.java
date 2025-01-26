package com.example.codesoftlution.petNovaBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medital_histories")
public class MedicalHistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetModel pet;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private UserModel veterinario;

    private LocalDateTime fechaConsulta;

    @Column(nullable = false)
    private String motivoConsulta;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private boolean active;

    @OneToMany(mappedBy = "medicalHistoryModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VaccineModel> vaccines;
}
