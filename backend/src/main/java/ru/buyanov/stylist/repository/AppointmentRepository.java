package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.buyanov.stylist.model.Appointment;
import ru.buyanov.stylist.model.projection.BusySlot;

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

    @Query( "SELECT " +
            "  new ru.buyanov.stylist.model.projection.StylistsBusyPerDay(" +
            "    a.date, " +
            "    a.slotDefinitionId " +
            "  )" +
            "FROM Appointment a " +
            "GROUP BY a.date, a.slotDefinitionId " +
            "HAVING count(a.stylistId) >= :maxNumberOfStylists")
    Collection<BusySlot> fetchNotAvailableSlots(@Param("maxNumberOfStylists") long maxNumberOfStylists);

}
