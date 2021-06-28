package com.revature.app;

import com.revature.controllers.ProductController;
import com.revature.daos.ProductDAO;
import com.revature.daos.ProductDAOPostgres;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        ProductDAO productDAO = new ProductDAOPostgres();
        ProductService productService = new ProductServiceImpl(productDAO);
        ProductController productController = new ProductController(productService);

        app.get("/products", productController.getAllProducts);

        app.get("/products/:id", productController.getProductById);

        app.get("/products/name/:name", productController.getProductByName);

        app.put("/products/:id", productController.updateProduct);

        app.start();
    }

}