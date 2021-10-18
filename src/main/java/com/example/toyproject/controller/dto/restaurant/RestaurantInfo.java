package com.example.toyproject.controller.dto.restaurant;

import com.example.toyproject.entity.Restaurant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInfo{
    private Long id;
    private LocalDate createDate;
    private String name;
    private String address;
    private String phoneNumber;

    private List<Long> orders;

    public RestaurantInfo(Restaurant restaurant){
        this.id = restaurant.getId();
        this.createDate = restaurant.getCreatedAt().toLocalDate();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.phoneNumber = restaurant.getPhoneNum();
        if(restaurant.getOrderList() != null){
            this.orders = new ArrayList<>();
            restaurant.getOrderList().stream()
                .forEach(order -> orders.add(order.getId()));
        }
    }
}
