package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
