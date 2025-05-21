package com.francisconicolau.prueba.application;

import com.francisconicolau.prueba.application.service.impl.PriceServiceImpl;
import com.francisconicolau.prueba.domain.model.Price;
import com.francisconicolau.prueba.infrastructure.persistence.JpaPriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private JpaPriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    private final Long productId = 35455L;
    private final Long brandId = 1L;


    @Test
    @DisplayName("Unico precio disponible")
    void test1_one_element() {
        var date = LocalDateTime.of(2020, 6, 14, 10, 0);
        var price = buildPrice(1, 0, new BigDecimal("35.50"), "2020-06-14T00:00:00", "2020-12-31T23:59:59");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(price));

        var actual = priceService.getApplicablePrice(date, productId, brandId);

        assertTrue(actual.isPresent());
        assertEquals(price, actual.get());
    }


    @Test
    @DisplayName("Devuelve varios elementos pero coge el primer precio de la lista cuando hay varios")
    void test2_empty_result() {
        var date = LocalDateTime.of(2020, 6, 14, 16, 0);
        var price1 = buildPrice(2, 1, new BigDecimal("25.45"), "2020-06-14T15:00:00", "2020-06-14T18:30:00");
        var price2 = buildPrice(1, 0, new BigDecimal("35.50"), "2020-06-14T00:00:00", "2020-12-31T23:59:59");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(price1, price2));

        var actual = priceService.getApplicablePrice(date, productId, brandId);

        assertTrue(actual.isPresent());
        assertEquals(price1, actual.get());
    }
    @Test
    @DisplayName("Lista vacia sin precios")
    void test3_empty_result() {
        var date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of());

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Manejo de excepción")
    void test4_throws_exception() {
        var date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenThrow(new RuntimeException("Error en BD"));

        var exception = assertThrows(RuntimeException.class, () ->
                priceService.getApplicablePrice(date, productId, brandId));

        assertEquals("Error en BD", exception.getMessage());
    }

    private Price buildPrice(int priceList, int priority, BigDecimal price, String start, String end) {
        return new Price(brandId, productId, priceList, priority, price,
                LocalDateTime.parse(start), LocalDateTime.parse(end), "EUR");
    }

}
