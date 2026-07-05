package com.sahana.restaurant_ordering_system.Service;

import java.util.List;

import com.sahana.restaurant_ordering_system.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahana.restaurant_ordering_system.entity.RestaurantTable;
import com.sahana.restaurant_ordering_system.repository.RestaurantTableRepository;

@Service
public class RestaurantTableService {

    @Autowired
    private RestaurantTableRepository repository;

    // Add Table
    public RestaurantTable saveTable(RestaurantTable table) {
        return repository.save(table);
    }

    // Get All Tables
    public List<RestaurantTable> getAllTables() {
        return repository.findAll();
    }
}