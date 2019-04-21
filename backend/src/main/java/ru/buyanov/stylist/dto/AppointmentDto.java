package ru.buyanov.stylist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    int id;
    int slotDefinitionId;
    int stylistId;
    LocalDate date;
    int customerId;
}
