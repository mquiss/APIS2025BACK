package com.api.ecommerce.product.controller;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<PageResponse<ProductDTO>> getProducts(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        PageResponse<ProductDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        ProductDTO product = productService.getProductById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }
}