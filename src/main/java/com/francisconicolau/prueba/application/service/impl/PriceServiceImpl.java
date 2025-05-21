package com.francisconicolau.prueba.application.service.impl;

import com.francisconicolau.prueba.application.service.PriceService;
import com.francisconicolau.prueba.domain.model.Price;
import com.francisconicolau.prueba.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    /**
     * Busca el precio para un producto, marca y fecha ordenados.
     * Consulta para obtener los precios que coinciden con los parámetros ordenados por prioridad
     * y devuelve el primero, que tenga mayor prioridad.
     * Si no hay resultados, devuelve un Optional vacío.
     *
     * @param date      the date and time
     * @param productId the ID of the product
     * @param brandId   the ID of the brand
     * @return Optional<Price>, or empty if none found
     */
    public Optional<Price> getApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findApplicablePrices(date, productId, brandId).stream().findFirst();
    }

}
