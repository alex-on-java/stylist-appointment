package ru.buyanov.stylist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreationDto {
    int slotDefinitionId;
    LocalDate date;
    int customerId;
}
