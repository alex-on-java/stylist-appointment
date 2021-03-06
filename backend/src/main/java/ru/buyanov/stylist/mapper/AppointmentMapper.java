package ru.buyanov.stylist.mapper;

import org.mapstruct.Mapper;
import ru.buyanov.stylist.dto.AppointmentCreationDto;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment create(AppointmentCreationDto dto, int stylistId);
    AppointmentDto toDto(Appointment entity);
}
