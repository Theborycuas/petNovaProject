package com.codesoftlution.petNova.user_microservice.respositories;


import com.codesoftlution.petNova.user_microservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserByEmailAndActive(String email, boolean active);
    UserModel findByTokenAndActive(String token, boolean active);
}
