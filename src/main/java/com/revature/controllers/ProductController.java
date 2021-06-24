package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.services.ProductService;
import io.javalin.http.Handler;

public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
}
