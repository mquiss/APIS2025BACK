package com.api.ecommerce.product.controller;

import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: prioridad
// - export const fetchAllProducts = () => api.get('/products');
// - export const fetchProductById = (id) => api.get(`/products/${id}`);
// - export const updateProduct = (id, data) => api.put(`/products/${id}`, data);
// - export const updateProductStock = (id, newStock) =>    api.patch(`/products/${id}`, { stock: newStock });
// - export const createProduct = (productData) => api.post('/products', productData);
// - export const deleteProduct = (id) => api.delete(`/products/${id}`);
// - export const fetchUserProducts = (userId) => api.get(`/products?userId=${userId}`);

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        ProductDTO product = productService.getProductById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest product) {
        ProductDTO productDTO = productService.createProduct(product);
        return productDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequest product) {
        ProductDTO productDTO = productService.updateProduct(id, product);
        return productDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String id) {
        ProductDTO productDTO = productService.deleteProduct(id);
        return productDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(productDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductDTO>> getProductsByUserId(@PathVariable String userId) {
        List<ProductDTO> products = productService.getProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }
}