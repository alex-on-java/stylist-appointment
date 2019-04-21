package ru.buyanov.stylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buyanov.stylist.dto.AppointmentDto;
import ru.buyanov.stylist.service.AppointmentService;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @RequestMapping(method = POST)
    public ResponseEntity<Void> createNewSlot(@RequestBody AppointmentDto dto) {
        service.createAppointment(dto);
        return ResponseEntity.created(URI.create("/slots/1")).build();
    }
}
