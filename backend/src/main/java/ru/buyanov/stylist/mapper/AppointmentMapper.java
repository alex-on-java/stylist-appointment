package ru.buyanov.stylist.mapper;

import org.mapstruct.Mapper;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment create(AppointmentDto dto);
    AppointmentDto toDto(Appointment entity);
}
