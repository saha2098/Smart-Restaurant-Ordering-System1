package com.sahana.restaurant_ordering_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahana.restaurant_ordering_system.dto.SalesReport;
import com.sahana.restaurant_ordering_system.entity.FoodItem;
import com.sahana.restaurant_ordering_system.entity.OrderEntity;
import com.sahana.restaurant_ordering_system.entity.OrderItemEntity;
import com.sahana.restaurant_ordering_system.repository.CreateRepository;
import com.sahana.restaurant_ordering_system.repository.FoodItemRepository;
import com.sahana.restaurant_ordering_system.repository.OrderItemRepository;

@Service
public class OrderService {

    @Autowired
    private CreateRepository repository;

    @Autowired
    private FoodItemRepository foodRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // ===========================
    // Order from Menu
    // ===========================
    public void placeOrder(Long tableId,
                           String customerName,
                           String phoneNumber,
                           List<Long> foodIds,
                           List<Integer> quantities) {


        OrderEntity order = new OrderEntity();

        order.setTableId(tableId);
        order.setCustomerName(customerName);
        order.setPhoneNumber(phoneNumber);
        order.setStatus("PENDING");

        double total = 0;

        OrderEntity savedOrder = repository.save(order);

        for (int i = 0; i < foodIds.size(); i++) {

            int qty = quantities.get(i);

            if (qty > 0) {

                FoodItem food = foodRepository.findById(foodIds.get(i)).orElse(null);

                if (food != null) {

                    total += food. getPrice() * qty;

                    OrderItemEntity item = new OrderItemEntity();
                    item.setOrderId(savedOrder.getId());
                    item.setFoodItemId(food.getId());
                    item.setQuantity(qty);

                    orderItemRepository.save(item);
                }
            }
        }

        savedOrder.setTotalAmount(total);

        repository.save(savedOrder);
    }

    // ===========================
    // REST API
    // ===========================

    public OrderEntity placeOrder(OrderEntity order) {
        return repository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return repository.findAll();
    }

    public OrderEntity updateStatus(Long id, OrderEntity order) {

        OrderEntity existingOrder =
                repository.findById(id).orElse(null);

        if (existingOrder != null) {
            existingOrder.setStatus(order.getStatus());
            return repository.save(existingOrder);
        }

        return null;
    }

    public OrderEntity updateTotalAmount(Long id, Double amount) {

        OrderEntity order =
                repository.findById(id).orElse(null);

        if (order != null) {

            order.setTotalAmount(amount);

            return repository.save(order);
        }

        return null;
    }

    public OrderEntity getOrderById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public List<OrderEntity> getPendingOrders() {

        return repository.findAll()
                .stream()
                .filter(order -> !"SERVED".equals(order.getStatus()))
                .toList();
    }

    public SalesReport getSalesReport() {

        List<OrderEntity> orders = repository.findAll();

        int totalOrders = orders.size();

        double totalRevenue = orders.stream()
                .filter(order -> order.getTotalAmount() != null)
                .mapToDouble(OrderEntity::getTotalAmount)
                .sum();

        return new SalesReport(totalOrders, totalRevenue);
    }
    public void changeStatus(Long orderId, String status) {

        OrderEntity order = repository.findById(orderId).orElse(null);

        if(order != null) {

            order.setStatus(status);

            repository.save(order);
        }
    }
    public List<OrderEntity> getOrdersByPhone(String phoneNumber) {

        return repository.findByPhoneNumber(phoneNumber);

    }

}