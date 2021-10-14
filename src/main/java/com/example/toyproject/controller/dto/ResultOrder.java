package com.example.toyproject.controller.dto;

import com.example.toyproject.entity.Order;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultOrder {
    private Long orderId;
    private String customerName;
    private String orderTime;
    private String restaurantName;
    private String restaurantPhone;
    private String restaurantAddress;

    public ResultOrder(Order order){
        this.orderId = order.getId();
        this.customerName = order.getCustomerName();
        this.orderTime = order.getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-mm-dd HH:MM:ss"));
        this.restaurantName = order.getRestaurant().getName();
        this.restaurantPhone = order.getRestaurant().getPhoneNum();
        this.restaurantAddress = order.getRestaurant().getAddress();
    }
}
