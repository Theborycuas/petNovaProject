package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.VaccineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVaccineRepository extends JpaRepository<VaccineModel, Long> {

    List<VaccineModel> findByMedicalHistoryModelId(Long medicalHistoryModelId);

    List<VaccineModel> findByNameContainingIgnoreCase(String name);

}
