package com.revature.servicetests;

import com.revature.daos.ProductDAO;
import com.revature.entities.Product;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTests {

    ProductDAO productDAO = Mockito.mock(ProductDAO.class);
    ProductService productService = new ProductServiceImpl(productDAO);

    @BeforeMethod
    void init(){
        List<Product> testProducts = new ArrayList<>();

        Product product1 = new Product(0, "Iced Coffee", "Coffee With Ice", 3.50F, "Happy", "Cold Drinks");
        Product product2 = new Product(0, "Cappuccino", "Coffee", 3.50F, "Sleepy", "Hot Drinks");
        Product product3 = new Product(0, "Mocha", "Coffee", 3.50F, "Sad", "Hot Drinks");

        testProducts.add(product1);
        testProducts.add(product2);
        testProducts.add(product3);

        Mockito.when(this.productDAO.getAllProducts()).thenReturn(testProducts);
    }

    @Test
    void getProductsByCategory(){
        List<Product> products = this.productService.retrieveProductsByCategory("Cold");
        Assert.assertEquals(products.size(), 1);
        Assert.assertEquals(products.get(0).getName(), "Iced Coffee");
    }

    @Test
    void getProductsByMood(){
        List<Product> products = this.productService.retrieveProductsByMood("Sleepy");
        Assert.assertEquals(products.size(), 1);
        Assert.assertEquals(products.get(0).getName(), "Cappuccino");
    }
}
