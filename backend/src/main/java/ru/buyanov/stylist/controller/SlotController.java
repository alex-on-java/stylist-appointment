package ru.buyanov.stylist.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("appointments")
public class SlotController {

    @RequestMapping(method = POST)
    public ResponseEntity<Void> createNewSlot() {
        return ResponseEntity.created(URI.create("/slots/1")).build();
    }
}
