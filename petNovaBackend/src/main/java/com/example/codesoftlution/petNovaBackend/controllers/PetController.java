package com.example.codesoftlution.petNovaBackend.controllers;

import com.example.codesoftlution.petNovaBackend.models.PetModel;
import com.example.codesoftlution.petNovaBackend.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            log.info("INICIO PETREGISTER");
            Optional<PetModel> mascotaEncontrada = petService.findByNameAndSpecieIdAndUserId(petModel.getName(), petModel.getSpecie().getId(), petModel.getUser().getId());
            if (!mascotaEncontrada.isPresent()) {
                petService.savePet(petModel);
                log.info("FIN PETREGISTER");
                return new ResponseEntity("MASCOTA CREADA", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("MASCOTA ENCONTRADA", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getPetsByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPetsByUser(@PathVariable("userId") Long userId) {
        try {

            log.info("INICIO GET PETS BY USER");

            List<PetModel> petModelList = petService.getAllPetsByUser(userId);

            log.info("FIN GET PETS BY USER");
            return new ResponseEntity(petModelList, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
