package ru.buyanov.stylist.logic;

import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.AvailabilityListDto;
import ru.buyanov.stylist.dto.DateAvailability;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.model.SlotDefinition;
import ru.buyanov.stylist.model.projection.BusySlot;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AvailabilityCalculationLogic {
    public AvailabilityListDto calculateAvailableSlots(
            Collection<BusySlot> busySlots,
            Collection<SlotDto> slots,
            LocalDate dateFrom,
            LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo) || slots.isEmpty()) {
            return new AvailabilityListDto(dateFrom, dateTo, Collections.emptyList());
        }
        Map<LocalDate, Set<Integer>> busySlotsPerDate = busySlots.stream()
                .collect(Collectors.groupingBy(
                        BusySlot::getDate, Collectors.mapping(BusySlot::getSlotDefinitionId, Collectors.toSet())
                ));
        List<DateAvailability> datesWithAvailableSlots = Stream
                .iterate(dateFrom, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(dateFrom, dateTo) + 1)
                .map(date -> {
                    Collection<Integer> busySlotsForDate = busySlotsPerDate.getOrDefault(date, Collections.emptySet());
                    Collection<SlotDto> availableSlots = slots.stream()
                            .filter(slot -> !busySlotsForDate.contains(slot.getSlotDefinitionId()))
                            .collect(Collectors.toList());
                    return new DateAvailability(date, availableSlots);
                })
                .collect(Collectors.toList());
        return new AvailabilityListDto(dateFrom, dateTo, datesWithAvailableSlots);
    }
}
