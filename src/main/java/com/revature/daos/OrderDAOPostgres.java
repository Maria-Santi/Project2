package com.revature.daos;

import com.revature.entities.Order;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOPostgres implements OrderDAO{

    @Override
    public Order createOrder(Order order) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into shop_order (order_date, status, u_id) values (?, ?, ?)";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getOrderDate());
            ps.setString(2, order.getStatus());
            ps.setInt(3, order.getUserId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("order_id");
            order.setOrderId(key);
            return order;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from shop_order";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while(rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getLong("order_date"));
                order.setStatus(rs.getString("status"));
                order.setUserId(rs.getInt("u_id"));
                orders.add(order);
            }
            return orders;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from shop_order where order_id=?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setOrderDate(rs.getLong("order_date"));
            order.setStatus(rs.getString("status"));
            order.setUserId(rs.getInt("u_id"));
            return order;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Order updateOrder(Order order) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "update shop_order set order_date=?, status=?, u_id=? where order_id=?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, order.getOrderDate());
            ps.setString(2, order.getStatus());
            ps.setInt(3, order.getUserId());
            ps.setInt(4, order.getOrderId());

            ps.execute();
            return order;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteOrderById(int orderId) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "delete from shop_order where order_id = ?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();
            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
