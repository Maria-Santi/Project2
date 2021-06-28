package com.revature.daos;

import com.revature.entities.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    Product getProductByName(String name);

    Product updateProduct(Product product);
}
