package com.example.codesoftlution.petNovaBackend.controllers;

import com.example.codesoftlution.petNovaBackend.models.OfficeModel;
import com.example.codesoftlution.petNovaBackend.repositories.IOfficeRepository;
import com.example.codesoftlution.petNovaBackend.services.OfficeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("apiPetNova/offices")
@CrossOrigin("*")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @Autowired
    HttpServletRequest request;

    Logger log = Logger.getLogger(OfficeController.class.getName());

    @RequestMapping(value = "/resgisterOffice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity resgisterOffice(
            @Valid @RequestParam Long userId,
            @Valid @RequestBody OfficeModel officeModel,
            @RequestParam(required = false) Long veterinarioId
    ) {
        try {
            log.info("START OFICE REGISTER");
            OfficeModel officeModel1 = officeService.officeRegister(userId, officeModel, veterinarioId);
            log.info("END OFICE REGISTER");
            return new ResponseEntity(officeModel1, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/listAllOffice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listAllOffice() {
        try {
            log.info("START OFICE LIST ALL OFICE");
            List<OfficeModel> officeModelList = officeService.getAllOffices();
            log.info("END OFICE LIST ALL OFICE");
            return new ResponseEntity(officeModelList, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getOfficeById/{officeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOfficeById(@PathVariable Long officeId) {
        try {
            log.info("START OFICE GET OFICE BY ID: ");
            Optional<OfficeModel> officeModel = Optional.ofNullable(officeService.getOfficeById(officeId)
                    .orElseThrow(() -> new RuntimeException("Consultorio no encontrado")));
            log.info("END OFICE GET OFICE BY ID: ");
            return new ResponseEntity(officeModel, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateOfficeById/{officeId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateOfficeById(
            @Valid @PathVariable Long officeId,
            @Valid @RequestParam Long userId,
            @Valid @RequestBody OfficeModel officeModel) {
        try {
            log.info("START OFFICE UPDATE BY ID");
            OfficeModel officeModelEncontrado = officeService.updateOffice(officeId, userId, officeModel);
            log.info("END OFIFCE UPDATE BY ID");
            return new ResponseEntity(officeModelEncontrado, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "deleteOfficeById/{officeId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteOfficeById(
            @Valid @PathVariable Long officeId,
            @Valid @RequestParam Long userId
    ){
        try {
            log.info("START OFICE DELETE BY ID");
            OfficeModel officeModelEncontrado = officeService.deleteOffice(officeId, userId);
            log.info("END OFICE DELETE BY ID");
            return new ResponseEntity(officeModelEncontrado, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
