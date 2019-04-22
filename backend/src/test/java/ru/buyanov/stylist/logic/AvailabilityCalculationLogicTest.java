package ru.buyanov.stylist.logic;

import org.junit.jupiter.api.Test;
import ru.buyanov.stylist.dto.AvailabilityListDto;
import ru.buyanov.stylist.dto.DateAvailability;
import ru.buyanov.stylist.dto.SlotDto;
import ru.buyanov.stylist.model.projection.BusySlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvailabilityCalculationLogicTest {
    private final AvailabilityCalculationLogic logic = new AvailabilityCalculationLogic();
    private final LocalDate firstDate = date("2010-10-20");
    private final LocalDate lastDate = date("2010-10-22");
    private final Collection<SlotDto> slots = slots(1, 4);
    private final Collection<BusySlot> busy = combine(busyDate(firstDate, 1, 3));

    @Test
    public void test_getTheSameDatesThatHaveBeenPassedAsArguments() {
        LocalDate expectedDateFrom = date("2010-01-31");
        LocalDate expectedDateTo = date("2010-11-01");

        AvailabilityListDto actual = logic.calculateAvailableSlots(busy, slots, expectedDateFrom, expectedDateTo);

        assertEquals(expectedDateFrom, actual.getDateFrom());
        assertEquals(expectedDateTo, actual.getDateTo());
    }

    @Test
    public void test_listSizeEqualToNumberOfDays() {
        AvailabilityListDto actual = logic.calculateAvailableSlots(busy, slots, firstDate, lastDate);

        assertEquals(3, actual.getDays().size());
    }

    @Test
    public void test_listIsEmptyForDateFromIsAfterDateTo() {
        AvailabilityListDto actual = logic.calculateAvailableSlots(busy, slots, lastDate, firstDate);

        assertTrue(actual.getDays().isEmpty());
    }

    @Test
    public void test_listIsEmptyForEmptySlots() {
        AvailabilityListDto actual = logic.calculateAvailableSlots(busy, Collections.emptyList(), firstDate, lastDate);

        assertTrue(actual.getDays().isEmpty());
    }

    @Test
    public void test_listHasOneElementForEqualDates() {
        AvailabilityListDto actual = logic.calculateAvailableSlots(busy, slots, firstDate, firstDate);

        assertEquals(1, actual.getDays().size());
    }

    @Test
    public void test_dayIsEmptyIfAllSlotsAreBusy() {
        Collection<BusySlot> allBusy = combine(busyDate(firstDate, 1, 2, 3, 4));

        AvailabilityListDto actual = logic.calculateAvailableSlots(allBusy, slots, firstDate, firstDate);
        Iterator<DateAvailability> daysIterator = actual.getDays().iterator();

        assertTrue(daysIterator.hasNext());
        assertTrue(daysIterator.next().getSlots().isEmpty());
    }

    @Test
    public void test_allSlotsAreAvailable() {
        Collection<BusySlot> noBusy = Collections.emptyList();
        Collection<SlotDto> tenSlots = slots(1, 10);

        AvailabilityListDto actual = logic.calculateAvailableSlots(noBusy, tenSlots, firstDate, firstDate);
        Iterator<DateAvailability> daysIterator = actual.getDays().iterator();

        assertTrue(daysIterator.hasNext());
        assertEquals(10, daysIterator.next().getSlots().size());
    }

    @Test
    public void test_complexLogic() {
        Collection<SlotDto> expectedSlots = Stream.of(
                slots.stream().filter(s -> s.getSlotDefinitionId() == 4),
                slots.stream(),
                slots.stream().filter(s -> s.getSlotDefinitionId() == 1)
        )
                .flatMap(s -> s)
                .collect(Collectors.toList());
        Collection<BusySlot> busySlots = combine(
                busyDate(firstDate, 1, 2, 3),
                busyDate(lastDate, 2, 3, 4)
        );

        List<SlotDto> actualSlots = logic.calculateAvailableSlots(busySlots, slots, firstDate, lastDate)
                .getDays().stream()
                .map(DateAvailability::getSlots)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(expectedSlots, actualSlots);
    }

    private LocalDate date(String dateInISOFormat) {
        return LocalDate.parse(dateInISOFormat);
    }

    private Collection<SlotDto> slots(int firstId, int lastId) {
        return IntStream.rangeClosed(firstId, lastId)
                .mapToObj(id -> new SlotDto(id, LocalTime.now(), 30))
                .collect(Collectors.toList());
    }

    private Stream<BusySlot> busyDate(LocalDate date, int... slotIds) {
        return Arrays.stream(slotIds)
                .mapToObj(id -> new BusySlot(date, id));
    }

    @SafeVarargs
    private final Collection<BusySlot> combine(Stream<BusySlot>... slotPerDates) {
        return Arrays.stream(slotPerDates)
                .flatMap(s -> s)
                .collect(Collectors.toList());
    }
}
