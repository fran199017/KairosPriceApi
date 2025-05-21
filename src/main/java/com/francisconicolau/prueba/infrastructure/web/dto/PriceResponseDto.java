package com.francisconicolau.prueba.infrastructure.web.dto;

import com.francisconicolau.prueba.domain.model.Price;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponseDto {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public PriceResponseDto(Price price) {
        this.productId = price.getProductId();
        this.brandId = price.getBrandId();
        this.priceList = price.getPriceList();
        this.price = price.getPrice();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
    }
}
