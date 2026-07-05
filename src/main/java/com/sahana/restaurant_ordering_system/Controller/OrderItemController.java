package com.sahana.restaurant_ordering_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.OrderItemService;
import com.sahana.restaurant_ordering_system.entity.OrderItemEntity;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @PostMapping
    public OrderItemEntity addOrderItem(@RequestBody OrderItemEntity orderItem) {
        return service.addOrderItem(orderItem);
    }

    @GetMapping
    public List<OrderItemEntity> getAllOrderItems() {
        return service.getAllOrderItems();
    }
}