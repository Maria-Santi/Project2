package com.revature.controllers;

import com.revature.services.OrderProductService;

public class OrderProductController {
    private OrderProductService opService;
    public OrderProductController(OrderProductService opService){
        this.opService = opService;
    }
}
