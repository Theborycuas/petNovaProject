package com.example.codesoftlution.petNovaBackend.repositories;


import com.example.codesoftlution.petNovaBackend.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleModel, Long> {
    boolean existsByRoleName(String rolName);

}
