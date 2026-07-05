package com.sahana.restaurant_ordering_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.OrderService;

@Controller
public class KitchenController {

    @Autowired
    private OrderService service;

    @GetMapping("/kitchen")
    public String kitchen(Model model) {

        model.addAttribute("orders", service.getPendingOrders());

        return "kitchen";
    }

    @PostMapping("/kitchen/status")
    public String updateStatus(@RequestParam Long orderId,
                               @RequestParam String status) {

        service.changeStatus(orderId, status);

        return "redirect:/kitchen";
    }

}