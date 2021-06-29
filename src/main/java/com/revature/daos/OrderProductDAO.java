package com.revature.daos;

import com.revature.entities.OrderProduct;

import java.util.List;

public interface OrderProductDAO {
    OrderProduct createOrderProduct(OrderProduct op);

    OrderProduct getOrderProductById(int opId);

    List<OrderProduct> getOrderProductsByOrderId(int orderId);

    OrderProduct updateOrderProduct(OrderProduct orderProduct);

    boolean deleteOrderProductById(int opId);
}
