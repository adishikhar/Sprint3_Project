package com.nisum.Catalog.controller;



import com.nisum.Catalog.entity.PromotionRequest;
import com.nisum.Catalog.entity.PromotionResponse;
import com.nisum.Catalog.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public String createPromotion(@RequestBody PromotionRequest request) {
        promotionService.createPromotion(request);
        return "Promo created successfully!";
    }

    @GetMapping
    public List<PromotionResponse> getPromotions() {
        return promotionService.getAllPromotions();
    }
}
