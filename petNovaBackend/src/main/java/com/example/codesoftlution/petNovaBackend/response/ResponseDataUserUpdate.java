package com.example.codesoftlution.petNovaBackend.response;

import com.example.codesoftlution.petNovaBackend.models.RoleModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDataUserUpdate {

    private String name;
    private String idNumber;
    private String username;

    private String email;

    private String phoneNumber;
    private String linkPerfilPhoto;

}
