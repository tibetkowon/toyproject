package com.example.toyproject.controller;

import com.example.toyproject.model.Restaurant;
import com.example.toyproject.services.restaurant.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public String insert(@RequestBody Restaurant restaurant){
        log.info("insert in!!!!!");
        log.info("restaurant == {} ",restaurant);

        service.insert(restaurant);
        return "OK";
    }

    @GetMapping
    public List<Restaurant> selectAll(){
       List<Restaurant> result = service.selectAll();
       log.info("result :: {}", result);
       return result;
    }

    @GetMapping("/{name}")
    public Restaurant selectOneByName(@PathVariable(name = "name") String name){
        return service.selectOne(name);
    }

    @PutMapping("/{name}")
    public String updateByName(@PathVariable String name,
                         @RequestBody Restaurant restaurant){
        log.info("name == {} , restaurant == {} ",name, restaurant);
        service.update(name,restaurant);
            return "OK";
    }

    @DeleteMapping("/{name}")
    public String deleteByName(@PathVariable String name){
        log.info("Delete name == {}", name);
        service.delete(name);
        return "OK";
    }
}
