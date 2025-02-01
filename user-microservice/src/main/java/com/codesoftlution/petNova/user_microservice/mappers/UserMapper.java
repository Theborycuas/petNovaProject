package com.codesoftlution.petNova.user_microservice.mappers;

import com.codesoftlution.petNova.user_microservice.dtos.UserDTO;
import com.codesoftlution.petNova.user_microservice.models.UserModel;

public class UserMapper {

    public static UserDTO toUserDTO(final UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setRollName(userModel.getRole().getRoleName());
        userDTO.setPhoneNumber(String.valueOf(userModel.getPhoneNumber()));

        return userDTO;

    }
}
