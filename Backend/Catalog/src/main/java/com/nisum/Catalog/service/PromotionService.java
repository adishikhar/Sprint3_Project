package com.nisum.Catalog.service;



import com.nisum.Catalog.entity.PromotionRequest;
import com.nisum.Catalog.entity.PromotionResponse;
import com.nisum.Catalog.model.PromotionType;
import com.nisum.Catalog.model.Promotions;
import com.nisum.Catalog.repository.PromotionTypeRepository;
import com.nisum.Catalog.repository.PromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    @Autowired
    private PromotionsRepository promotionsRepository;

    public void createPromotion(PromotionRequest request) {

        PromotionType promotionType = promotionTypeRepository
                .findByPromoType(request.getPromoType())
                .orElseGet(() -> promotionTypeRepository.save(PromotionType.builder()
                        .promoType(request.getPromoType())
                        .build()));


        String promoCode = generatePromoCode();


        Promotions promo = Promotions.builder()
                .promoCode(promoCode)
                .promotionType(promotionType)
                .description(request.getDescription())
                .promoAmount(request.getPromoAmount())
                .build();

        promotionsRepository.save(promo);
    }

    public List<PromotionResponse> getAllPromotions() {
        return promotionsRepository.findAll().stream().map(promo ->
                PromotionResponse.builder()
                        .promoCode(promo.getPromoCode())
                        .promoType(promo.getPromotionType().getPromoType())
                        .description(promo.getDescription())
                        .promoAmount(promo.getPromoAmount())
                        .build()
        ).collect(Collectors.toList());
    }

    private String generatePromoCode() {
        int random = new Random().nextInt(91) + 10;
        return "PROMO" + random;
    }
}

