package ru.buyanov.stylist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.model.SlotDefinition;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SlotDefinitionMapper {

    Collection<SlotDto> toDto(Collection<SlotDefinition> entities);

    @Mappings({
            @Mapping(target = "slotDefinitionId", source = "id"),
            @Mapping(target = "available", constant = "true")
    })
    SlotDto toDto(SlotDefinition entity);
}
