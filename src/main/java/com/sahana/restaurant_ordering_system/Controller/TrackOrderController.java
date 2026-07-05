package com.sahana.restaurant_ordering_system.Controller;

import com.sahana.restaurant_ordering_system.Service.OrderService;
import com.sahana.restaurant_ordering_system.entity.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TrackOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/track-order")
    public String track(HttpSession session, Model model) {

        String phone =
                (String) session.getAttribute("phoneNumber");

        List<OrderEntity> orders =
                orderService.getOrdersByPhone(phone);

        if(!orders.isEmpty()) {

            model.addAttribute("status",
                    orders.get(orders.size()-1).getStatus());

        }

        return "track-order";
    }
}