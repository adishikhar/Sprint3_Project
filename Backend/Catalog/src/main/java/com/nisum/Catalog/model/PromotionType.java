package com.nisum.Catalog.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promotion_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promoTypeId;

    @Column(nullable = false)
    private String promoType;
}
