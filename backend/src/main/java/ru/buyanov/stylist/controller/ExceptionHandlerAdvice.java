package ru.buyanov.stylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.buyanov.stylist.exception.AppointmentConflictException;
import ru.buyanov.stylist.exception.NoStylistsException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<String> handleAppointmentConflictException(AppointmentConflictException e) {
        return ResponseEntity.status(CONFLICT).header("X-Error", e.getMessage()).build();
    }

    @ExceptionHandler(NoStylistsException.class)
    public ResponseEntity<String> handleNoStylistsException(NoStylistsException e) {
        return ResponseEntity.status(NO_CONTENT).header("X-Error", e.getMessage()).build();
    }
}
