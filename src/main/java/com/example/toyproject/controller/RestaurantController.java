package com.example.toyproject.controller;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.services.restaurant.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResultEntity<Restaurant> insert(@RequestBody Restaurant restaurant){
        log.info("Restaurant insert start -----");
        return service.insert(restaurant);
    }

    @GetMapping
    public ResultEntity<List<Restaurant>> selectAll(){
        log.info("Restaurant selectAll start -----");
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResultEntity<Restaurant> selectOne(@PathVariable(name = "id") Long id){
        log.info("Restaurant selectAll start -----");
        return service.selectOne(id);
    }

    @PutMapping("/{id}")
    public ResultEntity<Restaurant> update(@PathVariable(name = "id") Long id,
                         @RequestBody Restaurant restaurant){
        log.info("Restaurant update start -----");
        return service.update(id,restaurant);
    }

    @DeleteMapping("/{id}")
    public ResultEntity<Restaurant> delete(@PathVariable(name = "id") Long id){
        log.info("Restaurant delete start -----");
        return service.delete(id);
    }
}
