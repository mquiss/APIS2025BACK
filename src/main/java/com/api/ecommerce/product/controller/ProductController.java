package com.api.ecommerce.product.controller;

import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.StockRequest;
import com.api.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: prioridad
// - export const fetchAllProducts = () => api.get('/products'); ✅
// - export const fetchProductById = (id) => api.get(`/products/${id}`); ✅
// - export const updateProduct = (id, data) => api.put(`/products/${id}`, data); ✅ falta testear
// - export const updateProductStock = (id, newStock) => api.patch(`/products/${id}`, { stock: newStock });
// - export const createProduct = (productData) => api.post('/products', productData); ✅ falta testear
// - export const deleteProduct = (id) => api.delete(`/products/${id}`); ✅ falta testear
// - export const fetchUserProducts = (userId) => api.get(`/products?userId=${userId}`) ; ✅
// - fetchProductByCategoryId ✅

// TODO: excepciones personalizadas

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/page/")
    public ResponseEntity<PageResponse<ProductResponse>> getProducts(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        PageResponse<ProductResponse> products = productService.getAllProductsPage(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse product = productService.getProductById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return productResponse == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return productResponse == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id) {
        ProductResponse productResponse = productService.deleteProduct(id);
        return productResponse == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponse>> getProductsByUserId(@PathVariable String userId) {
        List<ProductResponse> products = productService.getProductsByUserId(userId);
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String categoryId) {
        List<ProductResponse> products = productService.getProductsByCategory(categoryId);
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    // falta implementar
    @PatchMapping("/update/stock/{id}")
    public ResponseEntity<ProductResponse> updateStock(@PathVariable String id, @Valid @RequestBody StockRequest stockRequest) {
        return ResponseEntity.notFound().build();
    }
}