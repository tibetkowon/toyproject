package com.example.toyproject.controller;

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
    public ResponseEntity<ResultEntity> insert(@RequestBody Restaurant restaurant){
        log.info("Restaurant insert start -----");
        //값 확인해?
        ResultEntity result = service.insert(restaurant);

        log.info("Restaurant insert end -----");
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ResultEntity<List<Restaurant>>> selectAll(){
       List<Restaurant> result = service.selectAll();
       return result;
    }

    @GetMapping("/{id}")
    public Restaurant selectOne(@PathVariable(name = "id") Long id){
        return service.selectOne(id);
    }

    @PutMapping("/{id}")
    public String updateByName(@PathVariable(name = "id") Long id,
                         @RequestBody Restaurant restaurant){
        service.update(id,restaurant);
            return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteByName(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "OK";
    }
}
