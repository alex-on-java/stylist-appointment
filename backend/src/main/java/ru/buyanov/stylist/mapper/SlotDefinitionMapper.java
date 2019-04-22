package ru.buyanov.stylist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.model.SlotDefinition;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SlotDefinitionMapper {

    Collection<SlotDto> toDto(Collection<SlotDefinition> entities);

    @Mapping(
            target = "slotDefinitionId", source = "id"
    )
    SlotDto toDto(SlotDefinition entity);
}
