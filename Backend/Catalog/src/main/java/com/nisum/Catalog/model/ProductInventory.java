package com.nisum.Catalog.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productInventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInventory {

    @Id
    @Column(length = 50)
    private String SKU;

    @Column(nullable = false)
    private Integer quantity;
}
