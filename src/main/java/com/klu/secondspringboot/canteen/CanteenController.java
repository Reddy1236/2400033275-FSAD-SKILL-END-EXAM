package com.klu.secondspringboot.canteen;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CanteenController {

    private final OrderRepository orderRepository;

    public CanteenController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public String home() {
        return "College Canteen API is running. Use GET /orders or POST /orders to insert orders.";
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order o) {
        o.setId(null);
        if (o.getStatus() == null || o.getStatus().isBlank()) {
            o.setStatus("NEW");
        }
        return orderRepository.save(o);
    }

    @PutMapping("/orders/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody Order o) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) return "Order Not Found";

        Order old = existingOrder.get();
        old.setStudentName(o.getStudentName());
        old.setItems(o.getItems());
        old.setTotalAmount(o.getTotalAmount());
        old.setStatus(o.getStatus());
        orderRepository.save(old);
        return "Order Updated";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) return "Order Not Found";
        orderRepository.deleteById(id);
        return "Order Deleted";
    }
}
