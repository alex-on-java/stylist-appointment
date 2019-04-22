package ru.buyanov.stylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.buyanov.stylist.dto.AvailabilityListDto;
import ru.buyanov.stylist.service.AvailabilityService;

import java.time.LocalDate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("availability")
@AllArgsConstructor
public class AvailabilityController {
    private final AvailabilityService service;

    @RequestMapping(method = GET)
    public ResponseEntity<AvailabilityListDto> getList(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dateFrom,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dateTo) {
        return ResponseEntity.ok(service.getList(dateFrom, dateTo));
    }

}
