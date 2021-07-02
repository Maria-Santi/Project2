package com.revature.controllers;

import com.revature.entities.Order;
import com.revature.services.OrderService;
import com.google.gson.Gson;
import io.javalin.http.Handler;

import java.util.List;

public class OrderController {
    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    public Handler placeOrder = ctx -> {
        Gson gson = new Gson();
        Order order = gson.fromJson(ctx.body(), Order.class);
        this.orderService.addOrder(order);
        String orderJSON = gson.toJson(order);
        ctx.result(orderJSON);
        ctx.status(201);
    };

    public Handler getAllOrders = ctx -> {
      List<Order> orderList;
      orderList = this.orderService.retrieveAllOrders();
      Gson gson = new Gson();
      String ordersJSON = gson.toJson(orderList);
      ctx.result(ordersJSON);
      ctx.status(200);
      ctx.contentType("application/json");
    };

    public Handler getOrderById = ctx -> {
        try{
            int id = Integer.parseInt(ctx.pathParam("orderId"));
            Order order = this.orderService.retrieveOrderById(id);
            Gson gson = new Gson();
            String orderJSON = gson.toJson(order);
            ctx.result(orderJSON);
            ctx.status(200);

        }catch (RuntimeException e){
            ctx.result("The order could not be found");
            ctx.status(404);
        }
    };

    public Handler updateOrder = ctx -> {
      try{
          int id = Integer.parseInt(ctx.pathParam("orderId"));
          this.orderService.retrieveOrderById(id);
          Gson gson = new Gson();
          Order bodyOrder = gson.fromJson(ctx.body(), Order.class);
          bodyOrder.setOrderId(id);
          this.orderService.updateOrder(bodyOrder);
          String orderJSON = gson.toJson(bodyOrder);
          ctx.result(orderJSON);
          ctx.status(205);

      }catch (RuntimeException e){
          ctx.result("The order could not be found");
          ctx.status(404);
      }
    };

    public Handler deleteOrder = ctx -> {
        try{
            int id = Integer.parseInt(ctx.pathParam("orderId"));
            this.orderService.retrieveOrderById(id);

            this.orderService.deleteOrder(id);
            ctx.result("Successfully deleted");
            ctx.status(205);

        }catch (RuntimeException e){
            ctx.result("The order could not be found");
            ctx.status(404);
        }
    };
}
