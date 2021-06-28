package com.revature.daotests;

import com.revature.daos.OrderDAO;
import com.revature.daos.OrderDAOPostgres;
import com.revature.entities.Order;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OrderDAOTests {

    static OrderDAO orderDAO = new OrderDAOPostgres();
    static Order testOrder = new Order(0, 1, "Generated", 1);

    @Test(priority = 1)
    void createOrder(){
        Order order = orderDAO.createOrder(testOrder);
        Assert.assertNotEquals(order.getOrderId(), 0);
    }

    /**
     * HAVE TEAM LOOK AT THIS
     */
    @Test(priority = 2)
    void getOrderById(){
        Order order = orderDAO.getOrderById(testOrder.getOrderId());
        Assert.assertEquals(order.getUserId(), testOrder.getUserId());
    }

    @Test(priority = 3)
    void updateOrder(){
        testOrder.setStatus("Submitted");
        orderDAO.updateOrder(testOrder);
        Order order = orderDAO.getOrderById(testOrder.getOrderId());
        Assert.assertEquals(order.getStatus(), testOrder.getStatus());
    }

    @Test(priority = 4)
    void deleteOrder(){
        boolean result = orderDAO.deleteOrderById(testOrder.getOrderId());
        Assert.assertTrue(result);
    }

    @Test(priority = 5)
    void getAllOrders(){
        Order order1 = new Order(0, 1, "Submitted", 1);
        Order order2 = new Order(0, 1, "Submitted", 1);
        Order order3 = new Order(0, 1, "Submitted", 1);
        orderDAO.createOrder(order1);
        orderDAO.createOrder(order2);
        orderDAO.createOrder(order3);
        List<Order> orders = orderDAO.getAllOrders();
        Assert.assertTrue(orders.size() >= 3);

        orderDAO.deleteOrderById(order1.getOrderId());
        orderDAO.deleteOrderById(order2.getOrderId());
        orderDAO.deleteOrderById(order3.getOrderId());
    }

}
