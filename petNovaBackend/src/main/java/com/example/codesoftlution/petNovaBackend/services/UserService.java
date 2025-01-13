package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public List<UserModel> getUsers(){return iUserRepository.findAll();}

    public UserModel saveUser(UserModel userModel){return iUserRepository.save(userModel);}
}
