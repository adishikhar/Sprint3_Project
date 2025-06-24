package com.nisum.Catalog.entity;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    private String newProductName;
    private String newCategoryName;
    private String newSku;
    private String newSize;
    private String newColor;
    private Double newPrice;
    private Integer newInventoryCount;

    private String originalSku;
    private Integer originalProductId;
    private Integer originalCategoryId;
}

