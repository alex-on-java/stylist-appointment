package ru.buyanov.stylist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sa", name = "appointment")
@IdClass(SlotPK.class)
public class Appointment {
    @Id
    int slotDefinitionId;
    @Id
    int stylistId;
    @Id
    LocalDate date;
    int customerId;
}
