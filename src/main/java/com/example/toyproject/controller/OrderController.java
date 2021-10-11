package com.example.toyproject.controller;

import com.example.toyproject.model.Order;
import com.example.toyproject.model.Restaurant;
import com.example.toyproject.services.order.OrderService;
import com.example.toyproject.services.restaurant.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService service;
    RestaurantService restaurantService;

    public OrderController(OrderService orderService, RestaurantService restaurantService) {
        this.service = orderService;
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public String insert(@RequestBody Order order){
        log.info("Order Insert!!!!");
        log.info("order == {}", order);
        service.insert(order);
        return "OK";
    }

    @GetMapping
    public List<Order> selectAll(){
        log.info("Order SelectAll!!!!!");
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Order selectOne(@PathVariable Long id){
        log.info("Order SelectOne!!!!!");

        return service.selectOne(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        log.info("Order Delete!!!!!");
        service.delete(id);
        return "OK";
    }
}