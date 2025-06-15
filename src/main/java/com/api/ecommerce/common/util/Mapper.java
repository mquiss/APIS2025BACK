package com.api.ecommerce.common.util;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ObjectId mapStringToObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ObjectId string: " + e.getMessage());
        }
    }
}
