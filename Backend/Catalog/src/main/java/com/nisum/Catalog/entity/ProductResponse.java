package com.nisum.Catalog.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String productName;
    private String categoryName;
    private String sku;
    private String size;
    private String color;
    private Double price;
    private Integer inventoryCount;
}

