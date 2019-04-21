package ru.buyanov.stylist;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.buyanov.stylist.model.SlotPK;
import ru.buyanov.stylist.repository.AppointmentRepository;

import java.time.LocalDate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@Sql(value = "/stylists.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/clear.sql", executionPhase = AFTER_TEST_METHOD)
public class AppointmentIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void test_createNewAppointmentForExistingStylist() throws Exception {
        MockHttpServletRequestBuilder request = post("/appointments")
                .contentType("application/json")
                .content("{ \"slotDefinitionId\": 1, \"stylistId\": 1, \"customerId\": 1, \"date\": \"01.01.1971\" }");

        mvc.perform(request)
                .andExpect(status().isCreated());

        assertTrue(appointmentRepository.findById(new SlotPK(1, 1, LocalDate.of(1971, 1, 1))).isPresent());
    }
}
