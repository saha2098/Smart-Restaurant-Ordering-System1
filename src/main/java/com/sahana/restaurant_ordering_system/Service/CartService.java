package com.sahana.restaurant_ordering_system.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahana.restaurant_ordering_system.entity.FoodItem;
import com.sahana.restaurant_ordering_system.model.CartItem;
import com.sahana.restaurant_ordering_system.repository.FoodItemRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    @Autowired
    private FoodItemRepository foodRepository;

    @SuppressWarnings("unchecked")
    public List<CartItem> getCart(HttpSession session) {

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    public void addToCart(HttpSession session,
                          Long foodId,
                          int quantity) {

        List<CartItem> cart = getCart(session);

        FoodItem food = foodRepository.findById(foodId).orElse(null);

        if (food == null) {
            return;
        }

        for (CartItem item : cart) {

            if (item.getFoodId().equals(foodId)) {

                item.setQuantity(item.getQuantity() + quantity);

                return;
            }
        }

        CartItem item = new CartItem(
                food.getId(),
                food.getName(),
                food.getPrice(),
                quantity);

        cart.add(item);
    }

    public double getGrandTotal(HttpSession session) {

        return getCart(session)
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clearCart(HttpSession session) {

        session.removeAttribute("cart");
    }
    public void removeItem(HttpSession session, Long foodId) {

        List<CartItem> cart = getCart(session);

        cart.removeIf(item -> item.getFoodId().equals(foodId));
    }
    public void increaseQuantity(HttpSession session, Long foodId) {

        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {

            if (item.getFoodId().equals(foodId)) {

                item.setQuantity(item.getQuantity() + 1);

                return;
            }
        }
    }

    public void decreaseQuantity(HttpSession session, Long foodId) {

        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {

            if (item.getFoodId().equals(foodId)) {

                if (item.getQuantity() > 1) {

                    item.setQuantity(item.getQuantity() - 1);

                } else {

                    cart.remove(item);
                }

                return;
            }
        }
    }
}
