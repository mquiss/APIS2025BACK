package com.api.ecommerce.product.controller;

import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.StockRequest;
import com.api.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/page")
    public ResponseEntity<PageResponse<ProductResponse>> getProducts(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        PageResponse<ProductResponse> products = productService.getAllProductsPage(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        URI location = URI.create("/products/"+productResponse.getId());
        return ResponseEntity.created(location).body(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponse>> getProductsByUserId(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String userId) {
        List<ProductResponse> products = productService.getProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/{userId}/page")
    public ResponseEntity<PageResponse<ProductResponse>> getProductByUserId(Pageable pageable, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String userId) {
        PageResponse<ProductResponse> products = productService.getProductsByUserId(userId, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String categoryId) {
        List<ProductResponse> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/update/{id}/stock")
    public ResponseEntity<ProductResponse> updateStock(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id, @Valid @RequestBody StockRequest stockRequest) {
        ProductResponse product = productService.updateStock(id, stockRequest);
        return ResponseEntity.ok(product);
    }
}