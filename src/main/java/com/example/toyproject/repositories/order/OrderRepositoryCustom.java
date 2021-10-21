package com.example.toyproject.repositories.order;

import com.example.toyproject.entity.restaurant.Restaurant;

public interface OrderRepositoryCustom {

    int deleteByRestaurant(Restaurant restaurant);
}
