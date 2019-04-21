package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.model.Slot;
import ru.buyanov.stylist.repository.SlotRepository;

@Service
@AllArgsConstructor
public class SlotService {
    private final SlotRepository repository;

    public void createSlot(SlotDto dto) {
        Slot slot = new Slot(dto.getSlotDefinitionId(), dto.getStylistId(), dto.getDate(), dto.getCustomerId());
        repository.save(slot);
    }
}
