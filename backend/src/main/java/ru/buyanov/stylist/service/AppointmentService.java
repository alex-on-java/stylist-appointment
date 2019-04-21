package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.repository.AppointmentRepository;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;

    public void createAppointment(AppointmentDto dto) {
        Appointment appointment = new Appointment(dto.getSlotDefinitionId(), dto.getStylistId(), dto.getDate(), dto.getCustomerId());
        repository.save(appointment);
    }
}
