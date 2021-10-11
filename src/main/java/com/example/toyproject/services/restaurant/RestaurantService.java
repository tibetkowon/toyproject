package com.example.toyproject.services.restaurant;

import com.example.toyproject.model.Restaurant;
import com.example.toyproject.services.common.BaseService;

import java.util.List;
import java.util.Optional;

public interface RestaurantService extends BaseService<Restaurant> {

    public void insert(Restaurant restaurant);

    public List<Restaurant> selectAll();

    public Restaurant selectOne(Long id);

    public Restaurant selectOne(String name);

    public void update(String name, Restaurant restaurant);

    public void delete(String name);
}
