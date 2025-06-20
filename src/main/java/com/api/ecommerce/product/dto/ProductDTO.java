package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class ProductDTO { // TODO: borrar para la entrega definitiva
    private ObjectId userId;
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private int discountPercentage;
    private boolean isFeatured;
}