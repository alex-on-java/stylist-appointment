package ru.buyanov.stylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buyanov.stylist.dto.AppointmentCreationDto;
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
    public ResponseEntity<Void> createNewAppointment(@RequestBody AppointmentCreationDto dto) {
        AppointmentDto createdAppointmentDto = service.createAppointment(dto);
        URI location = URI.create(String.format("/appointments/%d", createdAppointmentDto.getId()));
        return ResponseEntity.created(location).build();
    }
}
