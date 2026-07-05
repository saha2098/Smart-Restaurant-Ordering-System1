package com.sahana.restaurant_ordering_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahana.restaurant_ordering_system.entity.FoodItem;
import com.sahana.restaurant_ordering_system.repository.FoodItemRepository;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository repository;

    // Add Food
    public FoodItem saveFood(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    // Get All Foods
    public List<FoodItem> getAllFoods() {
        return repository.findAll();
    }

    // Get Food By Id
    public FoodItem getFoodById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update Food
    public FoodItem updateFood(Long id, FoodItem foodItem) {

        FoodItem existingFood = repository.findById(id).orElse(null);

        if (existingFood != null) {
            existingFood.setName(foodItem.getName());
            existingFood.setPrice(foodItem.getPrice());
            existingFood.setCategory(foodItem.getCategory());

            return repository.save(existingFood);
        }

        return null;
    }

    // Delete Food
    public String deleteFood(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Food Item Deleted Successfully";
        }

        return "Food Item Not Found";
    }
}