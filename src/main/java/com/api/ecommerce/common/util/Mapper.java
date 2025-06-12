package com.api.ecommerce.common.util;

import org.bson.types.ObjectId;

public class Mapper {
    public ObjectId mapStringToObjectId(String id) {
        return new ObjectId(id);
    }
}
