package com.example.toyproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/test")
    public String test(){
        log.info("in test");
        return "111";
    }
}
