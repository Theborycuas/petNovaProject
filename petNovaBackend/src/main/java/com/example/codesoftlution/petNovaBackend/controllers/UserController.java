package com.example.codesoftlution.petNovaBackend.controllers;


import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.response.ListUserResponse;
import com.example.codesoftlution.petNovaBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {
    Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUsers() {
        try {
            List<UserModel> userModelList = userService.getUsers();

            ListUserResponse listUserResponse = new ListUserResponse();
            listUserResponse.setUserModels(userModelList);
            listUserResponse.setMessage("Lista de Usuarios");

            return new ResponseEntity<>(listUserResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/getOk", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getOk() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
