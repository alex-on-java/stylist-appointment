package ru.buyanov.stylist.exception;

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException(Throwable cause) {
        super(cause);
    }
}
