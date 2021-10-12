package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.services.common.BaseService;

import java.util.List;

public interface RestaurantService extends BaseService<Restaurant> {

    boolean validate(Restaurant restaurant);

    ResultEntity<Restaurant> insert(Restaurant restaurant);

    List<Restaurant> selectAll();

    Restaurant selectOne(Long id);

    void update(Long id, Restaurant restaurant);

    void delete(Long id);
}
