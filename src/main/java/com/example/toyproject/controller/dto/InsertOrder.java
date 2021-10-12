package com.example.toyproject.controller.dto;

import com.example.toyproject.model.Order;
import lombok.Getter;

@Getter
public class InsertOrder {
    private String customerName;

    private Long restaurantId;

    public static void main(String[] args) {
        Order order = new Order();
    }
}