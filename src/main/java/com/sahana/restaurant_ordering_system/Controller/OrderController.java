package com.sahana.restaurant_ordering_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sahana.restaurant_ordering_system.Service.OrderService;
import com.sahana.restaurant_ordering_system.dto.SalesReport;
import com.sahana.restaurant_ordering_system.entity.OrderEntity;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    // ==========================
    // Place Order From Menu Page
    // ==========================
    @PostMapping("/place")
    public String placeOrderFromMenu(
            @RequestParam Long tableId,
            @RequestParam String customerName,
            @RequestParam String phoneNumber,
            @RequestParam("foodId") List<Long> foodIds,
            @RequestParam("quantity") List<Integer> quantities,
            Model model) {

        service.placeOrder(
                tableId,
                customerName,
                phoneNumber,
                foodIds,
                quantities);

        model.addAttribute("message", "Order Placed Successfully!");

        return "success";
    }

    // ==========================
    // REST API
    // ==========================

    @ResponseBody
    @PostMapping
    public OrderEntity placeOrder(@RequestBody OrderEntity order) {
        return service.placeOrder(order);
    }

    @ResponseBody
    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return service.getAllOrders();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public OrderEntity updateStatus(@PathVariable Long id,
                                    @RequestBody OrderEntity order) {

        return service.updateStatus(id, order);
    }

    @ResponseBody
    @PutMapping("/{id}/amount")
    public OrderEntity updateAmount(@PathVariable Long id,
                                    @RequestParam Double amount) {

        return service.updateTotalAmount(id, amount);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {

        return service.getOrderById(id);
    }

    @ResponseBody
    @GetMapping("/table/{tableId}")
    public List<OrderEntity> getOrdersByTable(@PathVariable Long tableId) {

        return service.getAllOrders()
                .stream()
                .filter(order -> order.getTableId().equals(tableId))
                .toList();
    }

    @ResponseBody
    @GetMapping("/kitchen")
    public List<OrderEntity> kitchenDashboard() {

        return service.getPendingOrders();
    }

    @ResponseBody
    @GetMapping("/report")
    public SalesReport getSalesReport() {

        return service.getSalesReport();
    }
}