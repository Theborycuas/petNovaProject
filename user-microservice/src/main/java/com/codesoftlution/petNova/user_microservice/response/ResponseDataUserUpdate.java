package com.codesoftlution.petNova.user_microservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
