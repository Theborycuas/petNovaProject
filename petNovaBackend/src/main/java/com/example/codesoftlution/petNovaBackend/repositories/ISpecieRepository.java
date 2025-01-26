package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.SpecieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecieRepository extends JpaRepository<SpecieModel, Long> {
    boolean existsByName(String name);

}
