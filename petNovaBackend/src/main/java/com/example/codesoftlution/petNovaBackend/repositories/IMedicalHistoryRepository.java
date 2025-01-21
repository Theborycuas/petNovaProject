package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.MedicalHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMedicalHistoryRepository extends JpaRepository<MedicalHistoryModel, Long> {
    List<MedicalHistoryModel> findByPetId(Long petId);
    List<MedicalHistoryModel> findByVeterinarioId(Long veterinarioId);
}
