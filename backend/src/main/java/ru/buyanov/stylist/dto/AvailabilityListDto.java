package ru.buyanov.stylist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityListDto {
    LocalDate dateFrom;
    LocalDate dateTo;
    Collection<DateAvailability> days;
}
