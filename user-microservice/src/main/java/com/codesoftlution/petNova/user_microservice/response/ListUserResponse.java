package com.codesoftlution.petNova.user_microservice.response;

import com.codesoftlution.petNova.user_microservice.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListUserResponse {
    private List<UserModel> userModels;
    private String message;
}
