package com.codesoftlution.petNova.pet_microservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String idNumber;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean active;
    private String token;
    private String firebaseToken;
    private LocalDateTime creationDate;
    private String linkPerfilPhoto;

}
