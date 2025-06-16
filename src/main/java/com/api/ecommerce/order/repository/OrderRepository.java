package com.api.ecommerce.order.repository;

import com.api.ecommerce.order.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    List<Order> findByUserId(ObjectId userId);
    Page<Order> findByUserId(ObjectId userId, Pageable pageable);
}