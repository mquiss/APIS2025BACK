package com.api.ecommerce.product.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Image {
    private String url;
    @Field("isCover")
    private boolean cover;
}

