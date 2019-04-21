package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.Appointment;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Stream<Appointment> findAllByDateAndSlotDefinitionId(LocalDate date, int slotDefinitionId);

    default Collection<Integer> findBusyStylists(LocalDate date, int slotDefinitionId) {
        return findAllByDateAndSlotDefinitionId(date, slotDefinitionId)
                .map(Appointment::getStylistId)
                .collect(Collectors.toList());
    }
}
