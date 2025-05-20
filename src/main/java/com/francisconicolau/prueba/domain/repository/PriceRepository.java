package com.francisconicolau.prueba.domain.repository;


import com.francisconicolau.prueba.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :productId AND p.brandId = :brandId " +
            "ORDER BY p.priority DESC")
    List<Price> findApplicablePrices(@Param("applicationDate") LocalDateTime applicationDate,
                                    @Param("productId") Long productId,
                                    @Param("brandId") Long brandId);

}
