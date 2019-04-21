package ru.buyanov.stylist.model.projection;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
public class BusySlot {
    LocalDate date;
    int slotDefinitionId;
}