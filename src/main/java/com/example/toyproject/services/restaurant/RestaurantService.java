package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.restaurant.InsertRestaurant;
import com.example.toyproject.controller.dto.restaurant.RestaurantInfo;
import com.example.toyproject.controller.dto.restaurant.ResultRestaurant;
import com.example.toyproject.entity.restaurant.Restaurant;
import java.util.List;

public interface RestaurantService {

    ResultEntity<ResultRestaurant> insert(InsertRestaurant insertRestaurant);

    ResultEntity<List<RestaurantInfo>> selectAll();

    ResultEntity<RestaurantInfo> selectOne(Long id);

    ResultEntity<ResultRestaurant> update(Long id, Restaurant restaurant);

    ResultEntity<ResultRestaurant> delete(Long id);
}
