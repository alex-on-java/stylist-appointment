package ru.buyanov.stylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthCheckController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Feeling good today");
    }
}
