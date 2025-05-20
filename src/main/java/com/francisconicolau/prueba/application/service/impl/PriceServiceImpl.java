package com.francisconicolau.prueba.application.service.impl;

import com.francisconicolau.prueba.application.service.PriceService;
import com.francisconicolau.prueba.domain.model.Price;
import com.francisconicolau.prueba.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public Optional<Price> getApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = priceRepository.findApplicablePrices(date, productId, brandId);
        return prices.stream().findFirst();
    }

}
