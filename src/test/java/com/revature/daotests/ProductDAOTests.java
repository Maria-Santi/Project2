package com.revature.daotests;


import com.revature.daos.ProductDAO;
import com.revature.daos.ProductDAOPostgres;
import com.revature.entities.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductDAOTests {

    static ProductDAO productDAO = new ProductDAOPostgres();
    static Product testProduct = new Product();

    @Test
    void getProductById(){
        testProduct.setProductId(1);
        Product product = productDAO.getProductById(testProduct.getProductId());
        Assert.assertEquals(product.getProductId(), testProduct.getProductId());
    }

    @Test
    void getProductByName(){
        testProduct.setName("Affogato");
        Product product = productDAO.getProductByName(testProduct.getName());
        Assert.assertEquals(product.getName(), testProduct.getName());
    }

    @Test
    void getAllProducts(){
        List<Product> products = productDAO.getAllProducts();
        Assert.assertTrue(products.size()>=3);
    }

}
