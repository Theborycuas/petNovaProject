package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.PetModel;
import com.example.codesoftlution.petNovaBackend.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private IPetRepository petRepository;

    public Optional<PetModel> findByNameAndSpecieIdAndUserId(String name, Long specieId, Long userId){
        return petRepository.findByNameAndSpecieIdAndUserId(name, specieId, userId);
    }

    public PetModel savePet(PetModel petModel) {
        return petRepository.save(petModel);
    }

    public List<PetModel> getAllPetsByUser(Long userId) {
        return petRepository.findByUserId(userId);
    }

    public PetModel getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no Encontrada"));
    }

    public PetModel updatePet(Long id, PetModel petModelUpdate) {
        PetModel petModel = getPetById(id);
        petModel.setName(petModelUpdate.getName());
        petModel.setRace(petModelUpdate.getRace());
        petModel.setAge(petModelUpdate.getAge());
        petModel.setColor(petModelUpdate.getColor());
        petModel.setActive(petModelUpdate.isActive());
        petModel.setObsevations(petModelUpdate.getObsevations());
        return petRepository.save(petModel);
    }

    public void deletePet(Long id) {
        PetModel petModel = getPetById(id);
        petModel.setActive(false);
        petRepository.save(petModel);
    }

}
