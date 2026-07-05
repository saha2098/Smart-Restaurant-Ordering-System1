package com.sahana.restaurant_ordering_system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome(
            @RequestParam(required = false) Long tableId,
            Model model) {

        if (tableId == null) {
            tableId = 1L;
        }

        model.addAttribute("tableId", tableId);

        return "welcome";
    }
}