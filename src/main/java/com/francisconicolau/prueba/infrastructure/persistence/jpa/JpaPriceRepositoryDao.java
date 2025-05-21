package com.francisconicolau.prueba.infrastructure.persistence.jpa;

import com.francisconicolau.prueba.infrastructure.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepositoryDao extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :productId AND p.brandId = :brandId " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrices(@Param("applicationDate") LocalDateTime applicationDate,
                                           @Param("productId") Long productId,
                                           @Param("brandId") Long brandId);
}
