package com.sahana.restaurant_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sahana.restaurant_ordering_system.entity.RestaurantTable;

public interface RestaurantTableRepository
        extends JpaRepository<RestaurantTable, Long> {

}