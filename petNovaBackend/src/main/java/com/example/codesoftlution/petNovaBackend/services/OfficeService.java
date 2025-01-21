package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.OfficeModel;
import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.IOfficeRepository;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    @Autowired
    private IOfficeRepository officeRepository;

    @Autowired
    private IUserRepository userRepository;

    public OfficeModel officeRegister(Long userId, OfficeModel officeModel, Long veterinarioId) {

        //Validar que exista el ususario que crea el consultorio
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));

        //Validar que se tenga el Rol para crear consultorio
        if(!"VETERINARIO".equalsIgnoreCase(userModel.getRole().getRoleName()) && !"OFFICE_ADMIN".equalsIgnoreCase(userModel.getRole().getRoleName())){
            throw new RuntimeException("Solo un administrador o un veterinario pueden registrar consultorios.");
        }

        //Validar Único nombre y telefono
        if(officeRepository.existsByName(officeModel.getName())){
            throw new RuntimeException("El consultorio ya existe");
        }
        if(officeRepository.existsByPhoneNumber(officeModel.getPhoneNumber())){
            throw new RuntimeException("El consultorio ya existe");
        }

        //Asociar al veterinario si es incluido
        if(veterinarioId != null){
            UserModel veterinario = userRepository.findById(veterinarioId)
                    .orElseThrow(() -> new RuntimeException("Veterinario no Encontrado"));

            if(!"VETERINARIO".equalsIgnoreCase(veterinario.getRole().getRoleName())){
                throw new RuntimeException("El Usuario asignado como responsable no es un Veterinario");
            }

            officeModel.setVeterinarioCreador(veterinario);
        }

        //Guardar y retornar el Consultorio
        return officeRepository.save(officeModel);
    }

    public List<OfficeModel> getAllOffices() {
        return officeRepository.findAll();
    }

    public Optional<OfficeModel> getOfficeById(Long officeId) {
        return officeRepository.findById(officeId);
    }

    public OfficeModel updateOffice(Long officeId, Long userId, OfficeModel officeModel) {

        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));

        //Validar que se tenga el Rol para crear consultorio
        if(!"VETERINARIO".equalsIgnoreCase(userModel.getRole().getRoleName()) && !"OFFICE_ADMIN".equalsIgnoreCase(userModel.getRole().getRoleName())){
            throw new RuntimeException("Solo un administrador o un veterinario pueden registrar consultorios.");
        }

        OfficeModel consultorioEncontrado = officeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Consultorio no Encontrado"));

        Optional.ofNullable(officeModel.getName()).ifPresent(consultorioEncontrado::setName);
        Optional.ofNullable(officeModel.getPhoneNumber()).ifPresent(consultorioEncontrado::setPhoneNumber);
        Optional.ofNullable(officeModel.getAddress()).ifPresent(consultorioEncontrado::setAddress);
        Optional.ofNullable(officeModel.getLinkLogoPhoto()).ifPresent(consultorioEncontrado::setLinkLogoPhoto);

        return officeRepository.save(consultorioEncontrado);
    }

    public OfficeModel deleteOffice(Long officeId, Long userId) {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));

        //Validar que se tenga el Rol para crear consultorio
        if(!"VETERINARIO".equalsIgnoreCase(userModel.getRole().getRoleName()) && !"OFFICE_ADMIN".equalsIgnoreCase(userModel.getRole().getRoleName())){
            throw new RuntimeException("Solo un administrador o un veterinario pueden registrar consultorios.");
        }

        OfficeModel consultorioEncontrado = officeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Consultorio no Encontrado"));

        consultorioEncontrado.setActive(false);
        return officeRepository.save(consultorioEncontrado);
    }
}
