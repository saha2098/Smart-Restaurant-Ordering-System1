package com.sahana.restaurant_ordering_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.FoodItemService;
import com.sahana.restaurant_ordering_system.entity.FoodItem;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemService service;

    // Add Food
    @PostMapping
    public FoodItem addFood(@RequestBody FoodItem foodItem) {
        return service.saveFood(foodItem);
    }

    // Get All Foods
    @GetMapping
    public List<FoodItem> getAllFoods() {
        return service.getAllFoods();
    }

    // Get Food By Id
    @GetMapping("/{id}")
    public FoodItem getFoodById(@PathVariable Long id) {
        return service.getFoodById(id);
    }

    // Update Food
    @PutMapping("/{id}")
    public FoodItem updateFood(@PathVariable Long id,
                               @RequestBody FoodItem foodItem) {
        return service.updateFood(id, foodItem);
    }

    // Delete Food
    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {

        service.deleteFood(id);

        return "Food Item Deleted";
    }
}