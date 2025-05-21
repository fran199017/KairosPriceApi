package com.francisconicolau.prueba.infrastructure.persistence.mapper;

import com.francisconicolau.prueba.domain.model.Price;
import com.francisconicolau.prueba.infrastructure.persistence.model.PriceEntity;

public class PriceEntityMapper {

    public static Price toDomain(PriceEntity entity) {
        return new Price(
            entity.getBrandId(),
            entity.getProductId(),
            entity.getPriceList(),
            entity.getPriority(),
            entity.getPrice(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getCurrency()
        );
    }
}
