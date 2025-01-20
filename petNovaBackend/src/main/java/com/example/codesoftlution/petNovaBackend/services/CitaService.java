package com.example.codesoftlution.petNovaBackend.services;

import com.example.codesoftlution.petNovaBackend.models.CitaModel;
import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private ICitaRepository citaRepository;
    @Autowired
    private UserService userService;

    public CitaModel registrarCitas(CitaModel cita) {

        // Validación: Verificamos si ya existe una cita en el mismo horario para el veterinario
        List<CitaModel> citas = citaRepository.findByVeterinarioIdAndFechaHoraBetween(
                cita.getVeterinario().getId(),
                cita.getFechaHora().minusMinutes(30),
                cita.getFechaHora().plusMinutes(30)
        );
        if (!citas.isEmpty()) {
            throw new RuntimeException("EL VETERINARIO YA TIENE UNA CITA EN ESTE HORARIO");
        }

        //Validar que el Usuario Doctor sea de tipo Veterinario
        return citaRepository.save(cita);
    }

    public List<CitaModel> listCitasByPets(Long idPet) {
        return citaRepository.findByPetId(idPet);
    }

    public CitaModel getCitaById(Long id) {
        return citaRepository.findById(id).orElseThrow(() -> new RuntimeException("El cita no existe"));
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

        cita.setActivo(false);
        citaRepository.save(cita);
    }

}
