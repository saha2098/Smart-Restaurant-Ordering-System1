package com.sahana.restaurant_ordering_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sahana.restaurant_ordering_system.Service.OrderService;

@Controller
public class ReportController {

    @Autowired
    private OrderService service;

    @GetMapping("/report")
    public String report(Model model) {

        model.addAttribute(
                "report",
                service.getSalesReport());

        return "report";
    }
}