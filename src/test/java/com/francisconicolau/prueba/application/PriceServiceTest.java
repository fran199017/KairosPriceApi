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
    @DisplayName("Test 1: 14 de junio a las 10:00")
    void test1() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expected = buildPrice(1, 0, new BigDecimal("35.50"),
                "2020-06-14T00:00:00", "2020-12-31T23:59:59");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(expected));

        Optional<Price> current = priceService.getApplicablePrice(date, productId, brandId);

        assertEquals(expected.getPrice(), current.get().getPrice());
        assertEquals(expected.getPriceList(), current.get().getPriceList());
    }

    @Test
    @DisplayName("Test 2: 14 de junio a las 16:00")
    void test2() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Price low = buildPrice(1, 0, new BigDecimal("35.50"), "2020-06-14T00:00:00", "2020-12-31T23:59:59");
        Price high = buildPrice(2, 1, new BigDecimal("25.45"), "2020-06-14T15:00:00", "2020-06-14T18:30:00");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(high, low));

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertEquals(high.getPrice(), actual.get().getPrice());
        assertEquals(high.getPriceList(), actual.get().getPriceList());
    }

    @Test
    @DisplayName("Test 3: 14 de junio a las 21:00")
    void test3() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        Price expected = buildPrice(1, 0, new BigDecimal("35.50"),
                "2020-06-14T00:00:00", "2020-12-31T23:59:59");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(expected));

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertEquals(expected.getPrice(), actual.get().getPrice());
    }

    @Test
    @DisplayName("Test 4: 15 de junio a las 10:00")
    void test4() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        Price expected = buildPrice(3, 1, new BigDecimal("30.50"),
                "2020-06-15T00:00:00", "2020-06-15T11:00:00");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(expected));

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertEquals(expected.getPrice(), actual.get().getPrice());
    }

    @Test
    @DisplayName("Test 5: 16 de junio a las 21:00")
    void test5() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        Price expected = buildPrice(4, 1, new BigDecimal("38.95"),
                "2020-06-15T16:00:00", "2020-12-31T23:59:59");

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(expected));

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertEquals(expected.getPrice(), actual.get().getPrice());
    }

    @Test
    @DisplayName("No se encuentra precio para la fecha, producto y marca")
    void test6_empty_result() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of());

        Optional<Price> actual = priceService.getApplicablePrice(date, productId, brandId);

        assertTrue(actual.isEmpty(), "Resultado vacio");
    }

    @Test
    @DisplayName("Manejo de excepción")
    void testRepository_throws_exception() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrices(date, productId, brandId))
                .thenThrow(new RuntimeException("Error en BD"));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                priceService.getApplicablePrice(date, productId, brandId));

        assertEquals("Error en BD", exception.getMessage());
    }

    private Price buildPrice(int priceList, int priority, BigDecimal price, String start, String end) {
        return new Price(brandId, productId, priceList, priority, price,
                LocalDateTime.parse(start), LocalDateTime.parse(end), "EUR");
    }
}
