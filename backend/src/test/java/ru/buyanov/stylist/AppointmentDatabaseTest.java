package ru.buyanov.stylist;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.buyanov.stylist.model.projection.BusySlot;
import ru.buyanov.stylist.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@AutoConfigureEmbeddedDatabase
@Sql(value = "/stylists-and-appointments.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/clear.sql", executionPhase = AFTER_TEST_METHOD)
public class AppointmentDatabaseTest {
    @Autowired
    private AppointmentRepository repository;

    private final LocalDate firstDate = LocalDate.parse("2010-10-10");
    private final LocalDate lastDate = LocalDate.parse("2010-10-11");

    @Test
    public void test_loadAllListedDatesAndSlotsForZeroStylists() {
        Collection<BusySlot> stats = repository.fetchNotAvailableSlots(0, firstDate, lastDate);

        assertEquals(5, stats.size());
    }

    @Test
    public void test_loadForActualNumberOfStylists() {
        Collection<BusySlot> expected = Collections.singletonList(new BusySlot(LocalDate.parse("2010-10-10"), 1));

        Collection<BusySlot> actual = repository.fetchNotAvailableSlots(3, firstDate, lastDate);

        assertEquals(expected, actual);
    }

    @Test
    public void test_loadNothingIfDatesAreNotMatch() {
        Collection<BusySlot> expected = Collections.emptyList();

        Collection<BusySlot> actual = repository.fetchNotAvailableSlots(0, lastDate.plusDays(1), lastDate.plusDays(2));

        assertEquals(expected, actual);
    }
}
