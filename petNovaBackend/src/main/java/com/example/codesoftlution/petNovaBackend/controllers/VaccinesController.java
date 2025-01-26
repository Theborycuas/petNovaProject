package com.example.codesoftlution.petNovaBackend.controllers;

import com.example.codesoftlution.petNovaBackend.models.VaccineModel;
import com.example.codesoftlution.petNovaBackend.services.VaccinesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/apiPetNova/vaccines")
@CrossOrigin
public class VaccinesController {
    @Autowired
    private VaccinesService vaccinesService;

    Logger log = Logger.getLogger(VaccinesController.class.getName());

    @RequestMapping(value = "/registerVaccine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity registerVaccine(@RequestParam Long id, @Valid @RequestBody VaccineModel vaccineModel) {
        try {
            log.info("START REGISTER VACCINE");
            VaccineModel vaccineSave = vaccinesService.regisaterVaccine(id, vaccineModel);
            log.info("END REGISTER VACCINE");

            return new ResponseEntity(vaccineSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getVaccinesByMedicalH/{medicalHId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity getVaccinesByMedicalH(@PathVariable Long medicalHId) {
        try {
            log.info("START GET VACCINE BY MEDICAL H");

            List<VaccineModel> vaccineModelList = vaccinesService.getAllVaccinesByMedicalH(medicalHId);
            log.info("END GET VACCINE BY MEDICAL H");
            return new ResponseEntity(vaccineModelList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getVaccineById/{vaccineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity getVaccineById(@PathVariable Long vaccineId) {
        try {
            log.info("START GET VACCINE BY ID");
            VaccineModel vaccineFound = vaccinesService.getVaccineById(vaccineId);
            log.info("END GET VACCINE BY ID");
            return new ResponseEntity(vaccineFound, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateVaccine/{vaccineId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity updateVaccine(@PathVariable Long vaccineId, @RequestBody VaccineModel vaccineModel) {
        try {
            log.info("START UPDATE VACCINE BY ID");
            VaccineModel vaccineFound = vaccinesService.updateVaccine(vaccineId, vaccineModel);
            log.info("END UPDATE VACCINE BY ID");
            return new ResponseEntity(vaccineFound, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteVaccine/{vaccineId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity deleteVaccine(@PathVariable Long vaccineId) {
        try {
            log.info("START DELETE VACCINE BY ID");
            vaccinesService.deleteVaccine(vaccineId);
            log.info("END DELETE VACCINE BY ID");
            return new ResponseEntity("VACUNA ELIMINADA", HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
