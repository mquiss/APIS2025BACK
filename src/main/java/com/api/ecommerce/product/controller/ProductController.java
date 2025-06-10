package com.api.ecommerce.product.controller;

import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.service.ProductService;
import com.api.ecommerce.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}