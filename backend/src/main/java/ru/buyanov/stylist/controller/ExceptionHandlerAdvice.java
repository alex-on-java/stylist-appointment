package ru.buyanov.stylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.buyanov.stylist.exception.AppointmentConflictException;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<String> handleAppointmentConflictException(AppointmentConflictException e) {
        return ResponseEntity.status(CONFLICT).body(e.getMessage());
    }
}
