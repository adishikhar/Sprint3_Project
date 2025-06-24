package com.nisum.Catalog.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionRequest {
    private String promoType;
    private String description;
    private Double promoAmount;
}

