package com.sahana.restaurant_ordering_system.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.sahana.restaurant_ordering_system.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
