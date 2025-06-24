package com.nisum.Catalog.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotions {

    @Id
    @Column(length = 50)
    private String promoCode;

    @ManyToOne
    @JoinColumn(name = "promoTypeId" )
    private PromotionType promotionType;

    @Column(length = 255)
    private String description;

    private Double promoAmount;
}

