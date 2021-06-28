package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.entities.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService{

    private OrderDAO orderDAO = null;

    public OrderServiceImpl(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @Override
    public Order addOrder(Order order) {
        return this.orderDAO.createOrder(order);
    }

    @Override
    public Order retrieveOrderById(int orderId) {
        return this.orderDAO.getOrderById(orderId);
    }

    @Override
    public List<Order> retrieveAllOrders() {
        return this.orderDAO.getAllOrders();
    }

    @Override
    public Order updateOrder(Order order) {
        return this.orderDAO.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return this.orderDAO.deleteOrderById(orderId);
    }
}
