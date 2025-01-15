package com.example.codesoftlution.petNovaBackend;

import com.example.codesoftlution.petNovaBackend.models.RoleModel;
import com.example.codesoftlution.petNovaBackend.repositories.IRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    private IRoleRepository iRoleRepository;

    @Autowired
    public DataInit(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @PostConstruct
    public void createDataPetNova() {
        if (!iRoleRepository.existsByRoleName("ADMIN")) {
            RoleModel role1 = new RoleModel();
            role1.setRoleName("ADMIN");
            role1.setDescription("Usuario ADMIN");
            iRoleRepository.save(role1);
        }

        if (!iRoleRepository.existsByRoleName("VETERINARIO")) {
            RoleModel role2 = new RoleModel();
            role2.setRoleName("VETERINARIO");
            role2.setDescription("Usuario VETERINARIO");
            iRoleRepository.save(role2);
        }

        if (!iRoleRepository.existsByRoleName("RECEPCIONISTA")) {
            RoleModel role3 = new RoleModel();
            role3.setRoleName("RECEPCIONISTA");
            role3.setDescription("Usuario RECEPCIONISTA");
            iRoleRepository.save(role3);
        }

        if (!iRoleRepository.existsByRoleName("USUARIO")) {
            RoleModel role4 = new RoleModel();
            role4.setRoleName("USUARIO");
            role4.setDescription("Usuario USUARIO");
            iRoleRepository.save(role4);
        }
    }
}
