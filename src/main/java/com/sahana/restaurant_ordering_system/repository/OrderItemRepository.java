package com.sahana.restaurant_ordering_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahana.restaurant_ordering_system.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findByOrderId(Long orderId);

}