package com.francisconicolau.prueba.application.service;

import com.francisconicolau.prueba.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {


    Optional<Price> getApplicablePrice(LocalDateTime date, Long productId, Long brandId);

}
