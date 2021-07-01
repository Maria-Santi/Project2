package com.revature.daos;

import com.revature.entities.Product;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPostgres implements ProductDAO{
    @Override
    public List<Product> getAllProducts() {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from product";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Product> products = new ArrayList<>();
            while(rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
                product.setMood(rs.getString("mood"));
                product.setCategory(rs.getString("category"));
                products.add(product);
            }
            return products;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getProductById(int productId) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from product where product_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Product product = new Product();
            product.setProductId(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getFloat("price"));
            product.setMood(rs.getString("mood"));
            product.setCategory(rs.getString("category"));

            return product;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getProductByName(String name) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from product where product_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Product product = new Product();
            product.setProductId(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getFloat("price"));
            product.setMood(rs.getString("mood"));
            product.setCategory(rs.getString("category"));

            return product;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

}
