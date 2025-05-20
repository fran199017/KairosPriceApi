package com.francisconicolau.prueba.infrastructure.web;

import com.francisconicolau.prueba.application.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                      @RequestParam Long productId,
                                      @RequestParam Long brandId) {

        return priceService.getApplicablePrice(date, productId, brandId)
                .map(price -> ResponseEntity.ok(Map.of(
                        "productId", price.getProductId(),
                        "brandId", price.getBrandId(),
                        "priceList", price.getPriceList(),
                        "startDate", price.getStartDate(),
                        "endDate", price.getEndDate(),
                        "price", price.getPrice()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
