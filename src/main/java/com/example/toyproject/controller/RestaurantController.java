package com.example.toyproject.controller;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.restaurant.InsertRestaurant;
import com.example.toyproject.controller.dto.restaurant.RestaurantInfo;
import com.example.toyproject.controller.dto.restaurant.ResultRestaurant;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.services.restaurant.RestaurantService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResultEntity<ResultRestaurant> insert(@Valid @RequestBody(required = false) InsertRestaurant insertRestaurant){
        log.info("Restaurant insert start -----");
        if(insertRestaurant == null){
            return new ResultEntity<>(ResponseCode.REQUEST_NULL);
        }
            return service.insert(insertRestaurant);
    }

    @GetMapping
    public ResultEntity<List<RestaurantInfo>> selectAll(){
        log.info("Restaurant selectAll start -----");
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public ResultEntity<ResultRestaurant> selectOne(@PathVariable(name = "id") Long id){
        log.info("Restaurant selectOne start -----");
        return service.selectOne(id);
    }

    @PutMapping("/{id}")
    public ResultEntity<ResultRestaurant> update(@PathVariable(name = "id") Long id,
                         @RequestBody Restaurant restaurant){
        log.info("Restaurant update start -----");
        return service.update(id,restaurant);
    }

    @DeleteMapping("/{id}")
    public ResultEntity<ResultRestaurant> delete(@PathVariable(name = "id") Long id){
        log.info("Restaurant delete start -----");
        return service.delete(id);
    }
}
