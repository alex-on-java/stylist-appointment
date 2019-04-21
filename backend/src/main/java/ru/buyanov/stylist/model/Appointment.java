package ru.buyanov.stylist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sa", name = "appointment")
public class Appointment {
    @Id
    int id;
    int slotDefinitionId;
    int stylistId;
    LocalDate date;
    int customerId;
}
