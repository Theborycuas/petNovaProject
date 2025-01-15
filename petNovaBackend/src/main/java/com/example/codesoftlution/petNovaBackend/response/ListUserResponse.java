package com.example.codesoftlution.petNovaBackend.response;

import com.example.codesoftlution.petNovaBackend.models.UserModel;
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
