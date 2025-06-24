package com.nisum.Catalog.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeleteRequest {
    private Integer productId;
    private Integer categoryId;
    private String sku;
}

