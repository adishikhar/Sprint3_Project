package com.nisum.Catalog.service;

import com.nisum.Catalog.entity.ProductDeleteRequest;
import com.nisum.Catalog.entity.ProductRequest;
import com.nisum.Catalog.entity.ProductResponse;
import com.nisum.Catalog.entity.ProductUpdateRequest;
import com.nisum.Catalog.model.*;
import com.nisum.Catalog.repository.CategoryRepository;
import com.nisum.Catalog.repository.ProductCategoryRepository;
import com.nisum.Catalog.repository.ProductInventoryRepository;
import com.nisum.Catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository CategoryRepository;

    @Autowired
    private ProductInventoryRepository ProductInventoryRepository;

    @Autowired
    private ProductCategoryRepository ProductCategoryRepository;

    public void addproduct(ProductRequest request) {

        Product product = productRepository.findByProductName(request.getProductName())
                .orElseGet(() -> productRepository.save(Product.builder()
                        .productName(request.getProductName())
                        .build()));

        Category category = CategoryRepository.findByCategoryName(request.getCategoryName())
                .orElseGet(() -> CategoryRepository.save(Category.builder()
                        .categoryName(request.getCategoryName())
                        .build()));

        ProductInventory inventory = ProductInventoryRepository.save(ProductInventory
                .builder()
                .SKU(request.getSku())
                .quantity(request.getInventoryCount())
                .build());

        ProductCategory productCategory = ProductCategoryRepository.save(ProductCategory
                .builder()
                .productId(product.getProductId())
                .categoryId(category.getCategoryId())
                .SKU(request.getSku())
                .size(request.getSize())
                .color(request.getColor())
                .price(request.getPrice())
                .inventoryCount(request.getInventoryCount())
                .build());
    }


    public List<ProductResponse> getAllProducts() {
        List<ProductCategory> productCategories = ProductCategoryRepository.findAll();

        return productCategories.stream().map(pc -> {
            Optional<Product> productOpt = productRepository.findById(pc.getProductId());
            Optional<Category> categoryOpt = CategoryRepository.findById(pc.getCategoryId());
            Optional<ProductInventory> inventoryOpt = ProductInventoryRepository.findById(pc.getSKU());

            return ProductResponse.builder()
                    .productName(productOpt.map(Product::getProductName).orElse("Unknown"))
                    .categoryName(categoryOpt.map(Category::getCategoryName).orElse("Unknown"))
                    .sku(pc.getSKU())
                    .size(pc.getSize())
                    .color(pc.getColor())
                    .price(pc.getPrice())
                    .inventoryCount(inventoryOpt.map(ProductInventory::getQuantity).orElse(0))
                    .build();
        }).collect(Collectors.toList());
    }

    public void updateProduct(ProductUpdateRequest request) {
        Integer oldProductId = request.getOriginalProductId();
        Integer oldCategoryId = request.getOriginalCategoryId();
        String oldSku = request.getOriginalSku();

        // 1. Product
        Product existingProduct = productRepository.findById(oldProductId).orElse(null);
        Product newProduct = existingProduct;

        if (existingProduct == null || !existingProduct.getProductName().equals(request.getNewProductName())) {
            newProduct = productRepository.findByProductName(request.getNewProductName())
                    .orElseGet(() -> productRepository.save(Product.builder()
                            .productName(request.getNewProductName())
                            .build()));
        }

        // 2. Category
        Category existingCategory = CategoryRepository.findById(oldCategoryId).orElse(null);
        Category newCategory = existingCategory;

        if (existingCategory == null || !existingCategory.getCategoryName().equals(request.getNewCategoryName())) {
            newCategory = CategoryRepository.findByCategoryName(request.getNewCategoryName())
                    .orElseGet(() -> CategoryRepository.save(Category.builder()
                            .categoryName(request.getNewCategoryName())
                            .build()));
        }

        // 3. Product Inventory (only if changed)
        Optional<ProductInventory> existingInventoryOpt = ProductInventoryRepository.findById(oldSku);
        boolean inventoryChanged = !request.getNewSku().equals(oldSku)
                || existingInventoryOpt.map(ProductInventory::getQuantity)
                .map(q -> !q.equals(request.getNewInventoryCount()))
                .orElse(true);

        if (inventoryChanged) {
            if (!request.getNewSku().equals(oldSku)) {
                existingInventoryOpt.ifPresent(inv -> ProductInventoryRepository.deleteById(oldSku));
            }

            ProductInventoryRepository.save(ProductInventory.builder()
                    .SKU(request.getNewSku())
                    .quantity(request.getNewInventoryCount())
                    .build());
        }

        // 4. Product Category (only if changed)
        ProductCategoryId oldId = new ProductCategoryId(oldProductId, oldCategoryId);
        Optional<ProductCategory> oldCategoryOpt = ProductCategoryRepository.findById(oldId);

        // 👇 Fix: extract final values for lambda
        final Integer newProductId = newProduct.getProductId();
        final Integer newCategoryId = newCategory.getCategoryId();

        boolean productCategoryChanged = oldCategoryOpt.map(pc ->
                !Objects.equals(pc.getSKU(), request.getNewSku()) ||
                        !Objects.equals(pc.getSize(), request.getNewSize()) ||
                        !Objects.equals(pc.getColor(), request.getNewColor()) ||
                        !Objects.equals(pc.getPrice(), request.getNewPrice()) ||
                        !Objects.equals(pc.getInventoryCount(), request.getNewInventoryCount()) ||
                        !Objects.equals(newProductId, oldProductId) ||
                        !Objects.equals(newCategoryId, oldCategoryId)
        ).orElse(true);

        if (productCategoryChanged) {
            ProductCategoryRepository.deleteById(oldId);

            ProductCategoryRepository.save(ProductCategory.builder()
                    .productId(newProductId)
                    .categoryId(newCategoryId)
                    .SKU(request.getNewSku())
                    .size(request.getNewSize())
                    .color(request.getNewColor())
                    .price(request.getNewPrice())
                    .inventoryCount(request.getNewInventoryCount())
                    .build());
        }
    }

    public void deleteProduct(ProductDeleteRequest request) {
        // 1. Delete from ProductCategory
        ProductCategoryId id = new ProductCategoryId(request.getProductId(), request.getCategoryId());
        ProductCategoryRepository.deleteById(id);

        // 2. Delete from ProductInventory
        ProductInventoryRepository.deleteById(request.getSku());

        // 3. Delete from Product
        productRepository.deleteById(request.getProductId());

        // 4. Delete from Category
        CategoryRepository.deleteById(request.getCategoryId());
    }


}
