package ru.buyanov.stylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.service.SlotService;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("appointments")
@AllArgsConstructor
public class SlotController {
    private final SlotService service;

    @RequestMapping(method = POST)
    public ResponseEntity<Void> createNewSlot(@RequestBody SlotDto dto) {
        service.createSlot(dto);
        return ResponseEntity.created(URI.create("/slots/1")).build();
    }
}
