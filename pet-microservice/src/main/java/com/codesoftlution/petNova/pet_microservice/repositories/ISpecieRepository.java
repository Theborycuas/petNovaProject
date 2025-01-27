package com.codesoftlution.petNova.pet_microservice.repositories;

import com.codesoftlution.petNova.pet_microservice.models.SpecieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecieRepository extends JpaRepository<SpecieModel, Long> {
    boolean existsByName(String name);

}
