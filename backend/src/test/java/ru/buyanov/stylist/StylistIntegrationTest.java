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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringContains.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@Sql(value = "/clear.sql", executionPhase = AFTER_TEST_METHOD)
public class StylistIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void test_stylistCanBeSaved() throws Exception {
        MockHttpServletRequestBuilder request = post("/stylists")
                .contentType("application/json")
                .content("{ \"name\": \"John Snow\", \"email\": \"john.snow@game.of.thones\" }");

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/stylists/1")));
    }

    @Test
    void test_newStylistCanBeRetrieved() throws Exception {
        mvc.perform(post("/stylists")
                .contentType("application/json")
                .content("{ \"name\": \"Snow White\", \"email\": \"snow.white@brothers.grimm\" }"));

        mvc.perform(get("/stylists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("snow.white@brothers.grimm")));
    }
}
