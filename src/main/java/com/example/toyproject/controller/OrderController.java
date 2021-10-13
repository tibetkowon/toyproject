package com.example.toyproject.controller;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.entity.Order;
import com.example.toyproject.controller.dto.InsertOrder;
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
    public ResultEntity<Order> insert(@RequestBody(required = false) InsertOrder order){
        return service.insert(order);
    }

    @GetMapping
    @SuppressWarnings("rawtypes")
    public ResultEntity selectAll(){
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResultEntity<List<Order>> selectByRestaurantId(@PathVariable Long id){
        return service.selectByRestaurantId(id);
    }

    @DeleteMapping("/{id}")
    public ResultEntity<Order> delete(@PathVariable Long id){

        return service.delete(id);
    }
}
