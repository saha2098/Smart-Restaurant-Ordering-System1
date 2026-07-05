package com.sahana.restaurant_ordering_system.Controller;

import java.util.ArrayList;
import java.util.List;

import com.sahana.restaurant_ordering_system.Service.CartService;
import com.sahana.restaurant_ordering_system.Service.OrderService;
import com.sahana.restaurant_ordering_system.model.CartItem;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("total", cartService.getGrandTotal(session));

        model.addAttribute("customerName",
                session.getAttribute("customerName"));

        model.addAttribute("phoneNumber",
                session.getAttribute("phoneNumber"));

        model.addAttribute("tableId",
                session.getAttribute("tableId"));

        return "checkout";
    }

    @PostMapping("/checkout/place")
    public String placeOrder(HttpSession session, Model model) {

        List<CartItem> cart = cartService.getCart(session);

        if (cart.isEmpty()) {
            model.addAttribute("message", "Cart is Empty!");
            return "cart";
        }

        Long tableId = (Long) session.getAttribute("tableId");
        String customerName = (String) session.getAttribute("customerName");
        String phoneNumber = (String) session.getAttribute("phoneNumber");

        List<Long> foodIds = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (CartItem item : cart) {
            foodIds.add(item.getFoodId());
            quantities.add(item.getQuantity());
        }

        orderService.placeOrder(
                tableId,
                customerName,
                phoneNumber,
                foodIds,
                quantities);

        cartService.clearCart(session);

        model.addAttribute("message", "🎉 Order Placed Successfully!");

        return "success";
    }
}