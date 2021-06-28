package com.revature.services;

import com.revature.entities.Product;

import java.util.List;

public interface ProductService {

    Product retrieveProductById(int productId);

    Product retrieveProductByName(String name);

    List<Product> retrieveProductsByCategory(String category);

    List<Product> retrieveProductsByMood(String mood);

    List<Product> retrieveAllProducts();

    Product updateProduct(Product product);

}
