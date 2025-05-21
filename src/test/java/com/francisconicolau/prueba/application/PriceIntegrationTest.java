package com.francisconicolau.prueba.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: 14 de junio a las 10:00")
    void test1() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5"))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 2: 14 de junio a las 16:00")
    void test2() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("25.45"))
                .andExpect(jsonPath("$.priceList").value(2));
    }

    @Test
    @DisplayName("Test 3: 14 de junio a las 21:00")
    void test3() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5"))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 4: 15 de junio a las 10:00")
    void test4() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("30.5"))
                .andExpect(jsonPath("$.priceList").value(3));
    }

    @Test
    @DisplayName("Test 5: 16 de junio a las 21:00")
    void test5() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("38.95"))
                .andExpect(jsonPath("$.priceList").value(4));
    }

    @Test
    @DisplayName("Test Error: No se encuentra tarifa para el rango de fechas")
    void test_not_found() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2025-01-01T00:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("NOT_FOUND"));
    }

    @Test
    @DisplayName("Test Error: Faltan parámetros de entrada")
    void test_bad_request() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2025-01-01T00:00:00")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"));
    }
}
