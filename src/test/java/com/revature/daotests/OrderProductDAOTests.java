package com.revature.daotests;

import com.revature.DTOs.OrderProductInfo;
import com.revature.daos.OrderProductDAO;
import com.revature.daos.OrderProductDaoPostgres;
import com.revature.entities.OrderProduct;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OrderProductDAOTests {

    static OrderProductDAO opDAO = new OrderProductDaoPostgres();
    static OrderProduct testOp = new OrderProduct(0, 32, 2, 50);

    @Test(priority = 1)
    void createOrderProduct() {
        OrderProduct op = opDAO.createOrderProduct(testOp);
        Assert.assertNotEquals(op.getOpId(),0);
    }

    @Test(priority = 2)
    void getOrderProductById() {
        OrderProduct op = opDAO.getOrderProductById(testOp.getOpId());
        Assert.assertEquals(op.getQuantity(), 50);
    }

    @Test(priority = 3)
    void updateOrderProduct() {
        testOp.setQuantity(100);
        opDAO.updateOrderProduct(testOp);
        OrderProduct op = opDAO.getOrderProductById(testOp.getOpId());
        Assert.assertEquals(op.getQuantity(), 100);
    }

    @Test(priority = 4)
    void deleteOrderProduct() {
        boolean result = opDAO.deleteOrderProductById(testOp.getOpId());
        Assert.assertTrue(result);
    }

    @Test(priority = 5, dependsOnMethods = "createOrderProduct")
    void getAllOrderProductsByOrderId() {
        OrderProduct op1 = new OrderProduct(0, 32, 2, 50);
        OrderProduct op2 = new OrderProduct(0, 32, 2, 100);
        OrderProduct op3 = new OrderProduct(0, 33, 2, 50);
        opDAO.createOrderProduct(op1);
        opDAO.createOrderProduct(op2);
        opDAO.createOrderProduct(op3);
        List<OrderProduct> ops = opDAO.getOrderProductsByOrderId(32);
        Assert.assertTrue(ops.size()==2);

        opDAO.deleteOrderProductById(op1.getOpId());
        opDAO.deleteOrderProductById(op2.getOpId());
        opDAO.deleteOrderProductById(op3.getOpId());
    }

    @Test(priority = 6, dependsOnMethods = "getAllOrderProductsByOrderId")
    void getAllOrderInfo() {
        OrderProduct op1 = new OrderProduct(0, 32, 2, 50);
        OrderProduct op2 = new OrderProduct(0, 32, 2, 100);
        OrderProduct op3 = new OrderProduct(0, 33, 2, 50);
        opDAO.createOrderProduct(op1);
        opDAO.createOrderProduct(op2);
        opDAO.createOrderProduct(op3);
        List<OrderProductInfo> infos = opDAO.getOrderInfo(32);
        Assert.assertTrue(infos.size()==2);

        opDAO.deleteOrderProductById(op1.getOpId());
        opDAO.deleteOrderProductById(op2.getOpId());
        opDAO.deleteOrderProductById(op3.getOpId());
    }
}
