package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.model.SlotPK;

public interface AppointmentRepository extends JpaRepository<Appointment, SlotPK> {

}
