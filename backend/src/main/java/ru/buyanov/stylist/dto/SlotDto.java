package ru.buyanov.stylist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {
    int slotDefinitionId;
    LocalTime start;
    int duration;
}
