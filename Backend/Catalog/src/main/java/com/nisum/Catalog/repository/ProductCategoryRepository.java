package com.nisum.Catalog.repository;



import com.nisum.Catalog.model.ProductCategory;
import com.nisum.Catalog.model.ProductCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId> {
}

