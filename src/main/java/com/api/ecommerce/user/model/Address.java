package com.api.ecommerce.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String state;
    private String country;
}
