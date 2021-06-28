package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.entities.Product;
import com.revature.services.ProductService;
import io.javalin.http.Handler;

import java.util.List;

public class ProductController {

    private ProductService productService;

    Gson gson = new Gson();

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    public Handler getProductById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Product product = this.productService.retrieveProductById(id);
        String productJSON = gson.toJson(product);
        ctx.result(productJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };

    public Handler getProductByName = ctx -> {
        String name = ctx.pathParam("name");
        Product product = this.productService.retrieveProductByName(name);
        String productJSON = gson.toJson(product);
        ctx.result(productJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };

    public Handler getAllProducts = ctx -> {
        String category = ctx.queryParam("category");
        String mood = ctx.queryParam("mood");

        List<Product> products;

        if(category != null) {
            products = this.productService.retrieveProductsByCategory(category);
        }
        else if (mood != null) {
            products = this.productService.retrieveProductsByMood(mood);
        }
        else {
            products = this.productService.retrieveAllProducts();
        }

        String productsJSON = gson.toJson(products);
        ctx.result(productsJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };


    public Handler updateProduct = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Product product = gson.fromJson(ctx.body(), Product.class);
        product.setProductId(id);
        this.productService.updateProduct(product);
        String productJSON = gson.toJson(product);
        ctx.result(productJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };


}
