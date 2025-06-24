package com.nisum.Catalog.controller;


import com.nisum.Catalog.entity.ProductDeleteRequest;
import com.nisum.Catalog.entity.ProductRequest;
import com.nisum.Catalog.entity.ProductResponse;
import com.nisum.Catalog.entity.ProductUpdateRequest;
import com.nisum.Catalog.model.Product;
import com.nisum.Catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String createProduct(@RequestBody ProductRequest request) {
        productService.addproduct(request);
        return "Product saved successfully";
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping
    public String updateProduct(@RequestBody ProductUpdateRequest request) {
        productService.updateProduct(request);
        return "Product updated successfully";
    }

    @DeleteMapping
    public String deleteProduct(@RequestBody ProductDeleteRequest request) {
        productService.deleteProduct(request);
        return "Product deleted successfully";
    }


}
