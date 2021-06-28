package com.revature.app;

import com.revature.controllers.OrderController;
import com.revature.controllers.ProductController;
import com.revature.daos.*;
import com.revature.entities.Order;
import com.revature.services.*;
import io.javalin.Javalin;


public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        OrderDAO orderDAO = new OrderDAOPostgres();
        OrderService orderService = new OrderServiceImpl(orderDAO);
        OrderController orderController = new OrderController(orderService);

        ProductDAO productDAO = new ProductDAOPostgres();
        ProductService productService = new ProductServiceImpl(productDAO);
        ProductController productController = new ProductController(productService);

        OrderProductDAO opDAO = new OrderProductDaoPostgres();
        OrderProductService opService = new OrderProductServiceImpl(opDAO);

        app.get("/products", productController.getAllProducts);

        app.get("/products/:id", productController.getProductById);

        app.get("/products/name/:name", productController.getProductByName);

        app.put("/products/:id", productController.updateProduct);


        app.post("/orders", orderController.placeOrder);

        app.get("/orders", orderController.getAllOrders);

        app.get("/orders/:orderId", orderController.getOrderById);

        app.put("/orders/:orderId", orderController.updateOrder);

        app.delete("/orders/:orderId", orderController.deleteOrder);

        app.start();
    }

}