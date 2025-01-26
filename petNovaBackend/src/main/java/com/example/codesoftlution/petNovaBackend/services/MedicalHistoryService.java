package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.CitaModel;
import com.example.codesoftlution.petNovaBackend.models.MedicalHistoryModel;
import com.example.codesoftlution.petNovaBackend.models.PetModel;
import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.IMedicalHistoryRepository;
import com.example.codesoftlution.petNovaBackend.repositories.IPetRepository;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicalHistoryService {
    @Autowired
    IMedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    IPetRepository petRepository;

    @Autowired
    IUserRepository userRepository;

    public MedicalHistoryModel saveMedicalH(MedicalHistoryModel medicalHistoryModel) {
        //Buscar mascota
            PetModel mascotaEncontrada = petRepository.findById(medicalHistoryModel.getPet().getId())
                .orElseThrow(() -> new RuntimeException("MASCOTA NO ENCONTRADA"));

        //Buscar VETERINARIO
        UserModel veterinarioEncontrado = userRepository.findById(medicalHistoryModel.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("USUARIO NO ENCONTRADA"));
        if(!"VETERINARIO".equalsIgnoreCase(veterinarioEncontrado.getRole().getRoleName())){
            throw new RuntimeException("El Usuario no es VETERINARIO");
        }

        medicalHistoryModel.setPet(mascotaEncontrada);
        medicalHistoryModel.setVeterinario(veterinarioEncontrado);
        medicalHistoryModel.setFechaConsulta(LocalDateTime.now());

        return medicalHistoryRepository.save(medicalHistoryModel);

    }

    public MedicalHistoryModel getMedicalHById(Long id) {
        return medicalHistoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("La Historia Médica no existe"));
    }

    public void deleteMedicalH (Long medicalHId){
        MedicalHistoryModel medicalHistoryModel = getMedicalHById(medicalHId);
        medicalHistoryModel.setActive(false);
        medicalHistoryRepository.save(medicalHistoryModel);

    }
}
