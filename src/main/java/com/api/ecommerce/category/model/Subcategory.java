package com.api.ecommerce.category.model;

import java.util.List;

import com.api.ecommerce.product.model.Product;

import lombok.Data;

@Data
public class Subcategory {
    private String id;
    private String name;
    private List<Product> productos;
}
