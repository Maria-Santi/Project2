package com.revature.daos;

import com.revature.entities.OrderProduct;
import com.revature.exceptions.ResourceNotFound;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDaoPostgres implements OrderProductDAO {
    @Override
    public OrderProduct createOrderProduct(OrderProduct op) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into order_product (o_id, p_id, quantity) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,op.getoId());
            ps.setInt(2,op.getpId());
            ps.setInt(3,op.getQuantity());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            op.setOpId(key);
            return op;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderProduct getOrderProductById(int opId) throws ResourceNotFound {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from order_product where o_p_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, opId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            OrderProduct op = new OrderProduct();
            op.setOpId(rs.getInt("o_p_id"));
            op.setoId(rs.getInt("o_id"));
            op.setpId(rs.getInt("p_id"));
            op.setQuantity(rs.getInt("quantity"));

            return op;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ResourceNotFound("There is no OrderProduct by the id" + opId);
        }
    }

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) throws ResourceNotFound {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from order_product where o_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            List<OrderProduct> ops = new ArrayList<>();
            while (rs.next()) {
                OrderProduct op = new OrderProduct();
                op.setOpId(rs.getInt("o_p_id"));
                op.setoId(rs.getInt("o_id"));
                op.setpId(rs.getInt("p_id"));
                op.setQuantity(rs.getInt("quantity"));
                ops.add(op);
            }

            return ops;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ResourceNotFound("There are no OrderProducts for order id " + orderId);
        }
    }

    @Override
    public OrderProduct updateOrderProduct(OrderProduct orderProduct) throws ResourceNotFound {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "update order_product set o_id=?, p_id=?, quantity=? where o_p_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,orderProduct.getoId());
            ps.setInt(2,orderProduct.getpId());
            ps.setInt(3,orderProduct.getQuantity());
            ps.setInt(4,orderProduct.getOpId());

            ps.execute();
            return orderProduct;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ResourceNotFound("There is no OrderProduct by the id" + orderProduct.getOpId());
        }
    }

    @Override
    public boolean deleteOrderProductById(int opId) throws ResourceNotFound {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "delete from order_product where o_p_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, opId);
            ps.execute();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ResourceNotFound("There is no OrderProduct by the id" + opId);
        }
    }
}
