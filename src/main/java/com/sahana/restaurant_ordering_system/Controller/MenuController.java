package com.sahana.restaurant_ordering_system.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sahana.restaurant_ordering_system.repository.FoodItemRepository;

@Controller
public class MenuController {

    @Autowired
    private FoodItemRepository repository;

    @GetMapping("/menu")
    public String openMenu(
            @RequestParam Long tableId,
            @RequestParam String customerName,
            @RequestParam String phoneNumber,
            HttpSession session,
            Model model) {

        // Save customer details in session
        session.setAttribute("tableId", tableId);
        session.setAttribute("customerName", customerName);
        session.setAttribute("phoneNumber", phoneNumber);

        model.addAttribute("foods", repository.findAll());

        model.addAttribute("tableId", tableId);
        model.addAttribute("customerName", customerName);
        model.addAttribute("phoneNumber", phoneNumber);

        return "menu";
    }

    @GetMapping("/menu-v2")
    public String openMenuV2(
            @RequestParam Long tableId,
            @RequestParam String customerName,
            @RequestParam String phoneNumber,
            HttpSession session,
            Model model) {

        // Save values in session
        session.setAttribute("tableId", tableId);
        session.setAttribute("customerName", customerName);
        session.setAttribute("phoneNumber", phoneNumber);

        model.addAttribute("foods", repository.findAll());

        return "menu-v2";
    }
    @GetMapping("/menu-again")
    public String menuAgain(HttpSession session, Model model) {

        model.addAttribute("foods", repository.findAll());
        model.addAttribute("tableId", session.getAttribute("tableId"));
        model.addAttribute("customerName", session.getAttribute("customerName"));
        model.addAttribute("phoneNumber", session.getAttribute("phoneNumber"));

        return "menu-v2";
    }
}
