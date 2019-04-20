package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.SlotDefinition;

public interface SlotDefinitionRepository extends JpaRepository<SlotDefinition, Integer> {

}