package com.example.toyproject.controller.dto.order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InsertOrder {
    @NotBlank(message = "ORDER_NO_CUSTOMER_NAME")
    private String customerName;

    @NotBlank(message = "ORDER_NO_MENU")
    private String menuName;

    @NotNull(message = "ORDER_NO_RESTAURANT")
    private Long restaurantId;
}