package com.nisum.Catalog.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "product_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@IdClass(ProductCategoryId.class)
public class ProductCategory {

    @Id
    private Integer productId;

    @Id
    private Integer categoryId;

    @Column(length = 50)
    private String SKU;

    @Column(length = 10)
    private String size;

    private String color;

    private Double price;

    private Integer inventoryCount;

}
