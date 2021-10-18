package com.example.toyproject.controller.dto.restaurant;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsertRestaurant {

    @NotBlank(message = "RESTAURANT_NO_NAME")
    private String name;

    @NotBlank(message = "RESTAURANT_NO_ADDRESS")
    private String address;

    @NotBlank(message = "RESTAURANT_NO_PHONE_NUM")
    private String phoneNumber;
}
