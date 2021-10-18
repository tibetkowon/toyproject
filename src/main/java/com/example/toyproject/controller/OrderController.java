package com.example.toyproject.controller;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.order.InsertOrder;
import com.example.toyproject.controller.dto.order.ResultOrder;
import com.example.toyproject.services.order.OrderService;
import com.example.toyproject.services.restaurant.RestaurantService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultEntity<ResultOrder> insert(@Valid @RequestBody InsertOrder order){
        log.info("Order insert start -----");
        return service.insert(order);
    }

    @GetMapping
    public ResultEntity<Map<String, List<ResultOrder>>> selectAll(){
        log.info("Order selectAll start -----");
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResultEntity<List<ResultOrder>> selectByRestaurantId(@PathVariable(name = "id") Long id){
        log.info("Order selectByRestaurantId start -----");
        return service.selectByRestaurantId(id);
    }

    @DeleteMapping("/{id}")
    public ResultEntity<ResultOrder> delete(@PathVariable(name = "id") Long id){
        log.info("Order delete start -----");
        return service.delete(id);
    }

}
