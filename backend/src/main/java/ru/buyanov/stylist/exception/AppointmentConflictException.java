package ru.buyanov.stylist.exception;

import ru.buyanov.stylist.dto.AppointmentCreationDto;

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException(Throwable cause) {
        super(cause);
    }

    public AppointmentConflictException(AppointmentCreationDto request) {
        super(String.format("Cannot create appointment for this data: %s", request));
    }
}
