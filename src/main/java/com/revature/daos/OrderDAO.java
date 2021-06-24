package com.revature.daos;

import com.revature.entities.Order;

import java.util.List;

public interface OrderDAO {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    Order updateOrder(Order order);

    boolean deleteOrderById(int orderId);
}
