package ru.buyanov.stylist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.mapper.SlotDefinitionMapper;
import ru.buyanov.stylist.model.SlotDefinition;
import ru.buyanov.stylist.repository.SlotDefinitionRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class SlotDefinitionService {
    private final SlotDefinitionRepository repository;
    private final SlotDefinitionMapper mapper;

    public Collection<SlotDto> findAll() {
        List<SlotDefinition> all = repository.findAll();
        return mapper.toDto(all);
    }
}
