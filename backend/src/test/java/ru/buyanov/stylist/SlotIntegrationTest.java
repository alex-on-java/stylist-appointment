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

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@Sql(value = "/clear.sql", executionPhase = AFTER_TEST_METHOD)
public class SlotIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void test_createNewSlotForExistingStylist() throws Exception {
        MockHttpServletRequestBuilder request = post("/appointments")
                .contentType("application/json")
                .content("{ \"slotDefinitionId\": 1, \"stylistId\": 1, \"customerId\": 1, \"date\": \"01.01.1971\" }");

        mvc.perform(request)
                .andExpect(status().isCreated());
    }
}
