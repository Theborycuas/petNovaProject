package com.example.codesoftlution.petNovaBackend.controllers;

import com.example.codesoftlution.petNovaBackend.models.PetModel;
import com.example.codesoftlution.petNovaBackend.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("apiPetNova/pets")
@CrossOrigin("*")
public class PetController {

    @Autowired
    PetService petService;

    Logger log = Logger.getLogger(PetController.class.getName());

    @RequestMapping(value = "/petRegister", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity petRegister(@Valid @RequestBody PetModel petModel) {

        try {
            Optional<PetModel> mascotaEncontrada = petService.findByNameAndSpecieIdAndUserId(petModel.getName(), petModel.getSpecie().getId(), petModel.getUser().getId());
            if (!mascotaEncontrada.isPresent()) {
                petService.savePet(petModel);
                return new ResponseEntity("MASCOTA CREADA", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("MASCOTA ENCONTRADA", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
