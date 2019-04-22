package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.AvailabilityListDto;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.exception.NoStylistsException;
import ru.buyanov.stylist.logic.AvailabilityCalculationLogic;
import ru.buyanov.stylist.model.projection.BusySlot;
import ru.buyanov.stylist.repository.AppointmentRepository;
import ru.buyanov.stylist.repository.StylistRepository;

import java.time.LocalDate;
import java.util.Collection;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private final StylistRepository stylistRepository;
    private final AppointmentRepository appointmentRepository;
    private final SlotDefinitionService slotDefinitionService;
    private final AvailabilityCalculationLogic logic;


    public AvailabilityListDto getList(LocalDate dateFrom, LocalDate dateTo) {
        long overallStylistsCount = stylistRepository.count();
        if (overallStylistsCount == 0) {
            throw new NoStylistsException();
        }
        Collection<BusySlot> busySlots = appointmentRepository.fetchNotAvailableSlots(overallStylistsCount, dateFrom, dateTo);
        Collection<SlotDto> slotDefinitions = slotDefinitionService.findAll();
        return logic.calculateAvailableSlots(busySlots, slotDefinitions, dateFrom, dateTo);
    }
}
