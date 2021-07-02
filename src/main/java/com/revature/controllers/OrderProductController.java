package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.DTOs.OrderProductInfo;
import com.revature.entities.OrderProduct;
import com.revature.exceptions.ResourceNotFound;
import com.revature.services.OrderProductService;
import io.javalin.http.Handler;

import java.util.List;

public class OrderProductController {
    private OrderProductService opService;
    public OrderProductController(OrderProductService opService){
        this.opService = opService;
    }
    private Gson gson = new Gson();

    public Handler getAllOrderProductsByOrderId = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<OrderProduct> ops = this.opService.retrieveOrderProductsByOrderId(id);
            String opJSON = gson.toJson(ops);
            ctx.result(opJSON);
            ctx.status(200);
            ctx.contentType("application/JSON");
        } catch (ResourceNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getAllOrderInfo = ctx -> {
      try {
          int id = Integer.parseInt(ctx.pathParam("id"));
          List<OrderProductInfo> infos = this.opService.retrieveOrderInfo(id);
          String infoJSON = gson.toJson(infos);
          ctx.result(infoJSON);
          ctx.status(200);
          ctx.contentType("application/JSON");
      } catch (ResourceNotFound e) {
          ctx.result(e.getMessage());
          ctx.status(404);
      }
    };

    public Handler getOrderProductById = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            OrderProduct op = this.opService.retrieveOrderProductById(id);
            String opJSON = gson.toJson(op);
            ctx.result(opJSON);
            ctx.status(200);
            ctx.contentType("application/JSON");
        } catch (ResourceNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler createOrderProduct = ctx -> {
        OrderProduct op = gson.fromJson(ctx.body(), OrderProduct.class);
        this.opService.addOrderProduct(op);
        String opJSON = gson.toJson(op);
        ctx.result(opJSON);
        ctx.status(201);
    };

    public Handler updateOrderProduct = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            OrderProduct op = gson.fromJson(ctx.body(), OrderProduct.class);
            op.setOpId(id);
            this.opService.updateOrderProduct(op);
            String opJSON = gson.toJson(op);
            ctx.result(opJSON);
            ctx.status(200);
        } catch (ResourceNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler deleteOrderProductById = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            this.opService.deleteOrderProductById(id);
            ctx.result("Successfully deleted");
            ctx.status(205);
        } catch (ResourceNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler updateQuantity = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            int quantity = Integer.parseInt(ctx.pathParam("quantity"));
            this.opService.updateQuantity(id, quantity);
            ctx.result("Quantity Updated successfully");
            ctx.status(200);
        } catch (ResourceNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}
