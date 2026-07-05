package com.sahana.restaurant_ordering_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.OrderService;
import com.sahana.restaurant_ordering_system.entity.OrderEntity;

@Controller
public class HistoryController {

    @Autowired
    private OrderService service;

    @GetMapping("/history")
    public String historyPage() {
        return "history";
    }

    @PostMapping("/history")
    public String searchHistory(@RequestParam String phoneNumber,
                                Model model) {

        List<OrderEntity> orders =
                service.getOrdersByPhone(phoneNumber);

        model.addAttribute("orders", orders);
        model.addAttribute("phoneNumber", phoneNumber);

        return "history";
    }
}