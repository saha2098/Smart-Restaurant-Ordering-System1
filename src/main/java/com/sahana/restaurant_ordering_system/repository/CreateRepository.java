package com.sahana.restaurant_ordering_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sahana.restaurant_ordering_system.entity.OrderEntity;

public interface CreateRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByPhoneNumber(String phoneNumber);

}