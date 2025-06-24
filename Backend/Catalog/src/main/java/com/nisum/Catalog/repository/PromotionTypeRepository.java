package com.nisum.Catalog.repository;


import com.nisum.Catalog.model.PromotionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionTypeRepository extends JpaRepository<PromotionType, Integer> {
    Optional<PromotionType> findByPromoType(String promoType);
}

