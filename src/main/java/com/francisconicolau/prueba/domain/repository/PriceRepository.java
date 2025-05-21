package com.francisconicolau.prueba.domain.repository;

import com.francisconicolau.prueba.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId);
}
