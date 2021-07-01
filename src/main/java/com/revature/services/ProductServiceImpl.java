package com.revature.services;

import com.revature.daos.ProductDAO;
import com.revature.daos.ProductDAOPostgres;
import com.revature.entities.Product;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    private ProductDAO productDAO = new ProductDAOPostgres();

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product retrieveProductById(int productId) {
        return this.productDAO.getProductById(productId);
    }

    @Override
    public Product retrieveProductByName(String name) {
        return this.productDAO.getProductByName(name);
    }

    @Override
    public List<Product> retrieveProductsByCategory(String category) {
        List<Product> products = this.productDAO.getAllProducts();
        List<Product> catProducts = new ArrayList<>();
        for(Product p : products) {
            if(p.getCategory().contains(category)) {
                catProducts.add(p);
            }
        }
        return catProducts;
    }

    @Override
    public List<Product> retrieveProductsByMood(String mood) {
        List<Product> products = this.productDAO.getAllProducts();
        List<Product> moodProducts = new ArrayList<>();
        for(Product p : products) {
            if(p.getMood().equals(mood)) {
                moodProducts.add(p);
            }
        }
        return moodProducts;
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return this.productDAO.getAllProducts();
    }

}
