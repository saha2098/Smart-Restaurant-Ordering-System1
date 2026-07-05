package com.sahana.restaurant_ordering_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahana.restaurant_ordering_system.entity.OrderItemEntity;
import com.sahana.restaurant_ordering_system.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    // Add Order Item
    public OrderItemEntity addOrderItem(OrderItemEntity orderItem) {
        return repository.save(orderItem);
    }

    // Get All Order Items
    public List<OrderItemEntity> getAllOrderItems() {
        return repository.findAll();
    }
}