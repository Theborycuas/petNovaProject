package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPetRepository extends JpaRepository<PetModel, Long> {
    List<PetModel> findByUserId(Long userId);
    Optional<PetModel> findByNameAndSpecieIdAndUserId(String name, Long specieId, Long userId);
}
