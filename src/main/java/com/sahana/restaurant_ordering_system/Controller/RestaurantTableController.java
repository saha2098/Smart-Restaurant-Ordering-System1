package com.sahana.restaurant_ordering_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.RestaurantTableService;
import com.sahana.restaurant_ordering_system.entity.RestaurantTable;

@RestController
@RequestMapping("/tables")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService service;

    // Add Table
    @PostMapping
    public RestaurantTable addTable(@RequestBody RestaurantTable table) {
        return service.saveTable(table);
    }

    // Get All Tables
    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return service.getAllTables();
    }
}
