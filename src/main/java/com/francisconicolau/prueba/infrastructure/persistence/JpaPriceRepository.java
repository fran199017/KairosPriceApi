package com.francisconicolau.prueba.infrastructure.persistence;

import com.francisconicolau.prueba.domain.model.Price;
import com.francisconicolau.prueba.domain.repository.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private final JpaPriceRepositoryDao jpaRepository;

    public JpaPriceRepository(JpaPriceRepositoryDao jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        return jpaRepository.findApplicablePrices(applicationDate, productId, brandId);
    }
}
