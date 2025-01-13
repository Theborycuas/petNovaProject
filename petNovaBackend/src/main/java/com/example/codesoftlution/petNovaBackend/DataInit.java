package com.example.codesoftlution.petNovaBackend;

import com.example.codesoftlution.petNovaBackend.models.RolModel;
import com.example.codesoftlution.petNovaBackend.repositories.IRolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInit {
    private IRolRepository iRolRepository;

    @Autowired
    public DataInit(IRolRepository iRolRepository) {
        this.iRolRepository = iRolRepository;
    }

    @PostConstruct
    public void createDataPetNova() {
        if (!iRolRepository.existsByRolName("ADMIN")) {
            RolModel rol1 = new RolModel();
            rol1.setRolName("ADMIN");
            rol1.setDescription("Usuario ADMIN");
            iRolRepository.save(rol1);
        }

        if (!iRolRepository.existsByRolName("VETERINARIO")) {
            RolModel rol2 = new RolModel();
            rol2.setRolName("VETERINARIO");
            rol2.setDescription("Usuario VETERINARIO");
            iRolRepository.save(rol2);
        }

        if (!iRolRepository.existsByRolName("RECEPCIONISTA")) {
            RolModel rol3 = new RolModel();
            rol3.setRolName("RECEPCIONISTA");
            rol3.setDescription("Usuario RECEPCIONISTA");
            iRolRepository.save(rol3);
        }

        if (!iRolRepository.existsByRolName("USUARIO")) {
            RolModel rol4 = new RolModel();
            rol4.setRolName("USUARIO");
            rol4.setDescription("Usuario USUARIO");
            iRolRepository.save(rol4);
        }
    }
}
