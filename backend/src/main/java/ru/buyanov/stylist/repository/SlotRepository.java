package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.Slot;
import ru.buyanov.stylist.model.SlotPK;

public interface SlotRepository extends JpaRepository<Slot, SlotPK> {

}
