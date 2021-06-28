package com.revature.app;

import com.revature.controllers.OrderController;
import com.revature.controllers.ProductController;
import com.revature.daos.OrderDAO;
import com.revature.daos.OrderDAOPostgres;
import com.revature.daos.ProductDAO;
import com.revature.daos.ProductDAOPostgres;
import com.revature.entities.Order;
import com.revature.services.OrderService;
import com.revature.services.OrderServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;
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