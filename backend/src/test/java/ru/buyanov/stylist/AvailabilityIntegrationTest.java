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


import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@Sql(value = "/stylists-and-appointments.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/clear.sql", executionPhase = AFTER_TEST_METHOD)
public class AvailabilityIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void test_getDateInResponse() throws Exception {
        String expectedDateFrom = "1984-10-11";
        String expectedDateTo = "1984-10-12";
        String urlPath = String.format("/availability?dateFrom=%s&dateTo=%s", expectedDateFrom, expectedDateTo);
        mvc.perform(get(urlPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateFrom", equalTo(expectedDateFrom)))
                .andExpect(jsonPath("$.dateTo", equalTo(expectedDateTo)));
    }

    @Test
    void test_shouldReturnDaysList() throws Exception {
        String dateFrom = "2010-10-10";
        String dateTo = "2010-10-11";
        String urlPath = String.format("/availability?dateFrom=%s&dateTo=%s", dateFrom, dateTo);
        mvc.perform(get(urlPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days", hasSize(2)));
    }
}
