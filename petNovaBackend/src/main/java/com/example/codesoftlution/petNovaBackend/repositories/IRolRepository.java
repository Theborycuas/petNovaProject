package com.example.codesoftlution.petNovaBackend.repositories;


import com.example.codesoftlution.petNovaBackend.models.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolModel, Long> {
    boolean existsByRolName(String rolName);

}
