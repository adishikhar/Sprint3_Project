package com.nisum.Catalog.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryId implements Serializable {
    private Integer productId;
    private Integer categoryId;
}

