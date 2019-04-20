package ru.buyanov.stylist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotPK implements Serializable {
    int slotDefinitionId;
    int stylistId;
    LocalDate date;
}
