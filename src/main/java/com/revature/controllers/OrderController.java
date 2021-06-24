package com.revature.controllers;

import com.revature.services.OrderService;
import com.google.gson.Gson;
import io.javalin.http.Handler;

public class OrderController {
    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
}
