package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.CitaModel;
import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.ICitaRepository;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private ICitaRepository citaRepository;
    @Autowired
    private IUserRepository userRepository;

    public CitaModel registrarCitas(CitaModel cita) {

        //Validar que el Usuario sea de tipo VETERINARIO
        UserModel userVeterinario = userRepository.findById(cita.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

         if(!"VETERINARIO".equalsIgnoreCase(userVeterinario.getRole().getRoleName())){
            throw new RuntimeException("El usuario asignado no tiene el rol de veterinario.");
        }

        // Validación: Verificamos si ya existe una cita en el mismo horario para el veterinario
        List<CitaModel> citas = citaRepository.findByVeterinarioIdAndFechaHoraBetween(
                cita.getVeterinario().getId(),
                cita.getFechaHora().minusMinutes(30),
                cita.getFechaHora().plusMinutes(30)
        );
        if (!citas.isEmpty()) {
            throw new RuntimeException("EL VETERINARIO YA TIENE UNA CITA EN ESTE HORARIO");
        }

        return citaRepository.save(cita);
    }

    public List<CitaModel> listCitasByPets(Long idPet) {
        return citaRepository.findByPetId(idPet);
    }

    public CitaModel getCitaById(Long id) {
        return citaRepository.findById(id).orElseThrow(() -> new RuntimeException("La cita no existe"));
    }

    public CitaModel actualizarCita(Long id, CitaModel citaActualizada) {
        CitaModel cita = getCitaById(id);

        Optional.ofNullable(citaActualizada.getFechaHora()).ifPresent(cita::setFechaHora);
        Optional.ofNullable(citaActualizada.getMotivo()).ifPresent(cita::setMotivo);
        Optional.ofNullable(citaActualizada.getObsevations()).ifPresent(cita::setObsevations);
        Optional.ofNullable(citaActualizada.getVeterinario()).ifPresent(cita::setVeterinario);
        return citaRepository.save(cita);

    }

    public void eliminarCita(Long id) {
        CitaModel cita = getCitaById(id);

        cita.setActive(false);
        citaRepository.save(cita);
    }

}
