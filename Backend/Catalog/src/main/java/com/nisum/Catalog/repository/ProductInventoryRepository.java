package com.nisum.Catalog.repository;


import com.nisum.Catalog.model.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, String> {
}
