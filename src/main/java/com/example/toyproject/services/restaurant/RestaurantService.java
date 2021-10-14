package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.ResultRestaurant;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.services.common.BaseService;
import java.util.List;

public interface RestaurantService extends BaseService<Restaurant> {

    ResultEntity<ResultRestaurant> insert(Restaurant restaurant);

    ResultEntity<List<Restaurant>> selectAll();

    ResultEntity<Restaurant> selectOne(Long id);

    ResultEntity<ResultRestaurant> update(Long id, Restaurant restaurant);

    ResultEntity<ResultRestaurant> delete(Long id);
}
