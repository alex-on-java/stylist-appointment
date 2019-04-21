package ru.buyanov.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buyanov.stylist.model.Stylist;

import java.util.Collection;
import java.util.Optional;

public interface StylistRepository extends JpaRepository<Stylist, Integer> {
    Optional<Stylist> findFirstByIdNotInOrderByIdAsc(Collection<Integer> idList);
    Optional<Stylist> findFirstByOrderByIdAsc();

    default Optional<Integer> fetchFirstStylistIdThatIsNotInList(Collection<Integer> idList) {
        if (idList.isEmpty()) {
            return findFirstByOrderByIdAsc().map(Stylist::getId);
        }
        return findFirstByIdNotInOrderByIdAsc(idList).map(Stylist::getId);
    }
}
