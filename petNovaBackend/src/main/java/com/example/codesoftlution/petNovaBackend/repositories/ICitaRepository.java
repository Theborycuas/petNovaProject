package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.CitaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ICitaRepository extends JpaRepository<CitaModel, Long> {
    List<CitaModel> findByPetId(Long petId);
    List<CitaModel> findByVeterinarioIdAndFechaHoraBetween(Long veterinarioId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
