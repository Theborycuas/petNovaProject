package com.codesoftlution.petNova.pet_microservice;

import com.codesoftlution.petNova.pet_microservice.models.SpecieModel;
import com.codesoftlution.petNova.pet_microservice.repositories.ISpecieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    private ISpecieRepository iSpecieRepository;

    @Autowired
    public DataInit(ISpecieRepository iSpecieRepository) {
        this.iSpecieRepository = iSpecieRepository;
    }



    @PostConstruct
    public void createDataPetNova() {

        //CREATED DATA INIT SPECIES

        if(!iSpecieRepository.existsByName("PERRO")){
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
        }
    }
}
