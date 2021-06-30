package com.revature.services;

import com.revature.DTOs.OrderProductInfo;
import com.revature.daos.OrderProductDAO;
import com.revature.entities.OrderProduct;

import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductDAO opDAO = null;

    public OrderProductServiceImpl(OrderProductDAO opDAO) {
        this.opDAO = opDAO;
    }

    @Override
    public OrderProduct addOrderProduct(OrderProduct op) {
        return this.opDAO.createOrderProduct(op);
    }

    @Override
    public OrderProduct retrieveOrderProductById(int opId) {
        return this.opDAO.getOrderProductById(opId);
    }

    @Override
    public List<OrderProduct> retrieveOrderProductsByOrderId(int orderId) {
        return this.opDAO.getOrderProductsByOrderId(orderId);
    }

    @Override
    public List<OrderProductInfo> retrieveOrderInfo(int orderId) {
        return this.opDAO.getOrderInfo(orderId);
    }

    @Override
    public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
        return this.opDAO.updateOrderProduct(orderProduct);
    }

    @Override
    public boolean deleteOrderProductById(int opId) {
        return this.opDAO.deleteOrderProductById(opId);
    }

    @Override
    public boolean updateQuantity(int opId, int quantity) {
        OrderProduct op = this.opDAO.getOrderProductById(opId);
        op.setQuantity(quantity);
        this.opDAO.updateOrderProduct(op);
        return true;
    }
}
