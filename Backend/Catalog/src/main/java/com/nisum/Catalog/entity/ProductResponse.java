package com.nisum.Catalog.entity;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Integer productId;
    private Integer categoryId;
    private String productName;
    private String categoryName;
    private String sku;
    private String size;
    private String color;
    private Double price;
    private Integer inventoryCount;
}

