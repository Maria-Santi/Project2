package com.revature.services;

import com.revature.DTOs.OrderProductInfo;
import com.revature.entities.OrderProduct;

import java.util.List;

public interface OrderProductService {

    OrderProduct addOrderProduct(OrderProduct op);

    OrderProduct retrieveOrderProductById(int opId);

    List<OrderProduct> retrieveOrderProductsByOrderId(int orderId);

    List<OrderProductInfo> retrieveOrderInfo(int orderId);

    OrderProduct updateOrderProduct(OrderProduct orderProduct);

    boolean deleteOrderProductById(int opId);

    boolean updateQuantity(int opId, int quantity);

}
