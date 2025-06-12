package com.api.ecommerce.product.service;

import com.api.ecommerce.category.model.Subcategory;
import com.api.ecommerce.category.service.CategoryService;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import com.api.ecommerce.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> getAllProductsPage(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);

        List<ProductResponse> content = page.getContent()
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }

    public ProductResponse getProductById(String id) {
        return productRepository.findById(new ObjectId(id))
                .map(this::buildProductResponse)
                .orElse(null);
    }

    public List<ProductResponse> getProductsByCategory(String categoryId) {
        return productRepository.findByCategoryId(new ObjectId(categoryId))
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
        productRepository.save(product);
        return buildProductResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(new ObjectId(id)).orElse(null);
        // logica para pasar los valores de productrequest a product
        Product newProduct = ProductMapper.INSTANCE.toProduct(productRequest);
        newProduct.setId(product.getId());
        newProduct.setFeatured(product.isFeatured());
        productRepository.save(newProduct);
        return buildProductResponse(newProduct);
    }

    public ProductResponse deleteProduct(String id) {
        Product product = productRepository.findById(new ObjectId(id)).orElse(null);
        productRepository.delete(product);
        return buildProductResponse(product);
    }

    public List<ProductResponse> getProductsByUserId(String userId) {
        return productRepository.findByUserId(new ObjectId(userId))
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse buildProductResponse(Product product) {
        String username = userService.findUserById(product.getUserId()).getUsername();
        String categoryName = categoryService.findCategoryById(product.getCategoryId()).getName();
        List<String> subcategoryNames = categoryService.findCategoryById(product.getCategoryId())
                .getSubcategories()
                .stream()
                .map(Subcategory::getName)
                .collect(Collectors.toList());

        return ProductResponse.builder()
                .username(username)
                .category(categoryName)
                .subcategories(subcategoryNames)
                .title(product.getTitle())
                .images(product.getImages())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .discountPercentage(product.getDiscountPercentage())
                .isFeatured(product.isFeatured())
                .build();
    }
}