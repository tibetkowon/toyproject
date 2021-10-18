package com.example.toyproject.controller.dto.restaurant;

import com.example.toyproject.entity.Restaurant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultRestaurant {
    private Long id;
    private LocalDate createDate;
    private String name;
    private String address;
    private String phoneNumber;

    public ResultRestaurant(Restaurant restaurant){
        this.id = restaurant.getId();
        this.createDate = restaurant.getCreatedAt().toLocalDate();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.phoneNumber = restaurant.getPhoneNum();
    }
}
