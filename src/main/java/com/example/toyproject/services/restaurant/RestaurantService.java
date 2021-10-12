package com.example.toyproject.services.restaurant;

import com.example.toyproject.model.Restaurant;
import com.example.toyproject.services.common.BaseService;

import java.util.List;
import java.util.Optional;

public interface RestaurantService extends BaseService<Restaurant> {

    void insert(Restaurant restaurant);

    List<Restaurant> selectAll();

    Restaurant selectOne(Long id);

    Restaurant selectOne(String name);

    void update(String name, Restaurant restaurant);

    void delete(String name);
}
