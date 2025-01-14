package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public UserModel findUserByEmail(String email, boolean active) {
        return iUserRepository.findUserByEmailAndActive(email, active);
    }

    public List<UserModel> getUsers() {
        return iUserRepository.findAll();
    }

    public UserModel registerSetUser(UserModel userModel) {
        LocalDateTime dateNow = LocalDateTime.now();
        userModel.setCreationDate(dateNow);
        userModel.setToken("987654321");
        return iUserRepository.save(userModel);
    }
}
