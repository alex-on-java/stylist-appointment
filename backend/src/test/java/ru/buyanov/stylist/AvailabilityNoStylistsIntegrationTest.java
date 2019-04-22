package ru.buyanov.stylist;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
public class AvailabilityNoStylistsIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void test_getDateInResponse() throws Exception {
        String urlPath = "/availability?dateFrom=1984-10-11&dateTo=1984-10-12";
        mvc.perform(get(urlPath))
                .andExpect(status().isNoContent());
    }
}
