package com.nisum.Catalog.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionResponse {
    private String promoCode;
    private String promoType;
    private String description;
    private Double promoAmount;
}

