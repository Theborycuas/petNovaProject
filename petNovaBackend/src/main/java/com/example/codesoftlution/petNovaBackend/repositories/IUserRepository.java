package com.example.codesoftlution.petNovaBackend.repositories;

import com.example.codesoftlution.petNovaBackend.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
