package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.MedicalHistoryModel;
import com.example.codesoftlution.petNovaBackend.models.VaccineModel;
import com.example.codesoftlution.petNovaBackend.repositories.IMedicalHistoryRepository;
import com.example.codesoftlution.petNovaBackend.repositories.IVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinesService {

    @Autowired
    private IVaccineRepository iVaccineRepository;

    @Autowired
    private IMedicalHistoryRepository medicalHistoryRepository;

    public VaccineModel regisaterVaccine(Long medicalHId, VaccineModel vaccineModel) {
        //Guardar vacuna relacionada a un Historial medico
        //IDEA: Si no se quiere poner la info en Historial, que se autorrellene con la info de la vacuna
        MedicalHistoryModel medicalHistoryFound = medicalHistoryRepository.findById(medicalHId)
                .orElseThrow(() -> new RuntimeException("HISTORIAL MEDICO NO ENCONTRADO"));

        vaccineModel.setMedicalHistoryModel(medicalHistoryFound);
        return iVaccineRepository.save(vaccineModel);
    }

    public List<VaccineModel> getAllVaccinesByMedicalH(Long medicalHId) {
        MedicalHistoryModel medicalHistoryFound = medicalHistoryRepository.findById(medicalHId)
                .orElseThrow(() -> new RuntimeException("HISTORIAL MEDICO NO ENCONTRADO"));

        return medicalHistoryFound.getVaccines();
    }

    public VaccineModel getVaccineById(Long vaccineId) {
        return iVaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new RuntimeException("VACUNA NO ENCONTRADO"));
    }

    public VaccineModel updateVaccine(Long id, VaccineModel vaccineModel) {

        VaccineModel vaccineFound = getVaccineById(id);

        Optional.ofNullable(vaccineModel.getName()).ifPresent(vaccineFound::setName);
        Optional.ofNullable(vaccineModel.getDateApplication()).ifPresent(vaccineFound::setDateApplication);
        Optional.ofNullable(vaccineModel.getNextDoseDate()).ifPresent(vaccineFound::setNextDoseDate);
        Optional.ofNullable(vaccineModel.getObservations()).ifPresent(vaccineFound::setObservations);

        return iVaccineRepository.save(vaccineFound);
    }

    public void deleteVaccine(Long vaccineId) {
        VaccineModel vaccineFound = getVaccineById(vaccineId);

        vaccineFound.setActive(false);
        iVaccineRepository.save(vaccineFound);
    }
}
