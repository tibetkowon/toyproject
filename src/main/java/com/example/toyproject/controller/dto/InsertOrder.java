package com.example.toyproject.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InsertOrder {
    @NotEmpty(message = "ORDER_NO_CUSTOMER_NAME")
    private String customerName;

    @NotNull(message = "ORDER_NO_RESTAURANT")
    private Long restaurantId;
}