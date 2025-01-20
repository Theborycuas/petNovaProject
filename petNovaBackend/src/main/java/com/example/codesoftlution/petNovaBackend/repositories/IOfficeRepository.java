package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOfficeRepository extends JpaRepository<OfficeModel, Long> {
    boolean existsByName(String name);
    boolean existsByPhoneNumber(String phoneNumber);
}
