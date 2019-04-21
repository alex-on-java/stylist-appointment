package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.mapper.AppointmentMapper;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.repository.AppointmentRepository;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto dto) {
        Appointment appointment = mapper.create(dto);
        Appointment savedAppointment = repository.save(appointment);
        return mapper.toDto(savedAppointment);
    }
}
