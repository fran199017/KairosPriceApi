package com.francisconicolau.prueba.infrastructure.web.controller;

import com.francisconicolau.prueba.application.service.PriceService;
import com.francisconicolau.prueba.domain.exception.PriceNotFoundException;
import com.francisconicolau.prueba.infrastructure.web.dto.PriceResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
@Validated
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam
            @NotNull(message = "La fecha es obligatoria")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(description = "Fecha", example = "2020-06-14T10:00:00")
            LocalDateTime date,

            @RequestParam
            @NotNull(message = "Id de producto es obligatorio")
            @Parameter(description = "ID del producto", example = "35455")
            Long productId,

            @RequestParam
            @NotNull(message = "El Id de la marca es obligatorio")
            @Parameter(description = "ID de la marca", example = "1")
            Long brandId) {

        return priceService.getApplicablePrice(date, productId, brandId)
                .map(price -> ResponseEntity.ok(new PriceResponseDto(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()
                )))
                .orElseThrow(() -> new PriceNotFoundException("No se han encontrado coincidencias de precio con los parámetros de entrada."));
    }
}
