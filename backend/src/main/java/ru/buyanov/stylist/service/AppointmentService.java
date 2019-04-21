package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.exception.AppointmentConflictException;
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
        Appointment newAppointment = mapper.create(dto);
        try {
            Appointment savedAppointment = repository.save(newAppointment);
            return mapper.toDto(savedAppointment);
        } catch (DataIntegrityViolationException e) {
            throw new AppointmentConflictException(e);
        }
    }
}
