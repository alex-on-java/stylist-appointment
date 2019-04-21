package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.buyanov.stylist.dto.AppointmentCreationDto;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.exception.AppointmentConflictException;
import ru.buyanov.stylist.mapper.AppointmentMapper;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.repository.AppointmentRepository;
import ru.buyanov.stylist.repository.StylistRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final StylistRepository stylistRepository;
    private final AppointmentMapper mapper;

    @Transactional
    public AppointmentDto createAppointment(AppointmentCreationDto dto) {
        Collection<Integer> busyStylistIds = appointmentRepository.findBusyStylists(dto.getDate(), dto.getSlotDefinitionId());
        int availableStylist = stylistRepository.fetchFirstStylistIdThatIsNotInList(busyStylistIds)
                .orElseThrow(() -> new AppointmentConflictException(dto));
        Appointment newAppointment = mapper.create(dto, availableStylist);
        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        return mapper.toDto(savedAppointment);
    }
}
