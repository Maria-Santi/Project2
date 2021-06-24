package com.revature.services;

import com.revature.entities.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    Order retrieveOrderById(int orderId);

    List<Order> retrieveAllOrders();

    Order updateOrder(Order order);

    boolean deleteOrder(int orderId);
}
