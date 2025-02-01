package com.codesoftlution.petNova.user_microservice;

import com.codesoftlution.petNova.user_microservice.models.RoleModel;
import com.codesoftlution.petNova.user_microservice.respositories.IRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    private IRoleRepository iRoleRepository;
    //private ISpecieRepository iSpecieRepository;

    @Autowired
    public DataInit(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
        //this.iSpecieRepository = iSpecieRepository;
    }



    @PostConstruct
    public void createDataPetNova() {

        //CREATED DATA INIT ROLS
        if (!iRoleRepository.existsByRoleName("SUPER_ADMIN")) {
            RoleModel role1 = new RoleModel();
            role1.setRoleName("SUPER_ADMIN");
            role1.setDescription("Usuario SUPER_ADMIN");
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

        if (!iRoleRepository.existsByRoleName("OFFICE_ADMIN")) {
            RoleModel role5 = new RoleModel();
            role5.setRoleName("OFFICE_ADMIN");
            role5.setDescription("Usuario OFFICE_ADMIN");
            iRoleRepository.save(role5);
        }

        //CREATED DATA INIT SPECIES

       /* if(!iSpecieRepository.existsByName("PERRO")){
            SpecieModel specie1 = new SpecieModel();
            specie1.setName("PERRO");
            iSpecieRepository.save(specie1);
        }

        if(!iSpecieRepository.existsByName("GATO")){
            SpecieModel specie2 = new SpecieModel();
            specie2.setName("GATO");
            iSpecieRepository.save(specie2);
        }

        if(!iSpecieRepository.existsByName("CABALLO")){
            SpecieModel specie3 = new SpecieModel();
            specie3.setName("CABALLO");
            iSpecieRepository.save(specie3);
        }

        if(!iSpecieRepository.existsByName("VACA")){
            SpecieModel specie4 = new SpecieModel();
            specie4.setName("VACA");
            iSpecieRepository.save(specie4);
        }

        if(!iSpecieRepository.existsByName("CERDO")){
            SpecieModel specie5 = new SpecieModel();
            specie5.setName("CERDO");
            iSpecieRepository.save(specie5);
        }

        if(!iSpecieRepository.existsByName("BORREGO")){
            SpecieModel specie6 = new SpecieModel();
            specie6.setName("BORREGO");
            iSpecieRepository.save(specie6);
        }

        if(!iSpecieRepository.existsByName("AVE")){
            SpecieModel specie7 = new SpecieModel();
            specie7.setName("AVE");
            iSpecieRepository.save(specie7);
        }

        if(!iSpecieRepository.existsByName("CONEJO")){
            SpecieModel specie8 = new SpecieModel();
            specie8.setName("CONEJO");
            iSpecieRepository.save(specie8);
        }*/
    }
}
