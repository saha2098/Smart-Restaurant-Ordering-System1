package com.sahana.restaurant_ordering_system.Controller;

import com.sahana.restaurant_ordering_system.Service.CartService;
import com.sahana.restaurant_ordering_system.entity.FoodItem;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long foodId,
                            @RequestParam int quantity,
                            HttpSession session) {

        if (quantity > 0) {
            cartService.addToCart(session, foodId, quantity);
        }

        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model,
                           HttpSession session) {

        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("total", cartService.getGrandTotal(session));

        return "cart";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session) {

        cartService.clearCart(session);

        return "redirect:/menu-v2";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long foodId,
                             HttpSession session) {

        cartService.removeItem(session, foodId);

        return "redirect:/cart";
    }
    @PostMapping("/increase")
    public String increase(@RequestParam Long foodId,
                           HttpSession session) {

        cartService.increaseQuantity(session, foodId);

        return "redirect:/cart";
    }

    @PostMapping("/decrease")
    public String decrease(@RequestParam Long foodId,
                           HttpSession session) {

        cartService.decreaseQuantity(session, foodId);

        return "redirect:/cart";
    }
}