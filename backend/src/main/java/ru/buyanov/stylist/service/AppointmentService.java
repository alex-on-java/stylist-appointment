package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.mapper.AppointmentMapper;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.repository.AppointmentRepository;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    public void createAppointment(AppointmentDto dto) {
        Appointment appointment = mapper.create(dto);
        repository.save(appointment);
    }
}
