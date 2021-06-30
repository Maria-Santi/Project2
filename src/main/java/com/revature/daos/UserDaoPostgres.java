package com.revature.daos;

import com.revature.entities.User;
import com.revature.exceptions.ResourceNotFound;
import com.revature.utils.ConnectionUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres extends Component implements UserDAO {

    @Override
    public User createUser(User user) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into shop_user (first_name, last_name, username, pass, is_employee) values (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,user.getFirstName());
            pst.setString(2,user.getLastName());
            pst.setString(3,user.getUsername());
            pst.setString(4,user.getPassword());
            pst.setBoolean(5,user.isEmployee());
            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("user_id");
            user.setUserId(key);
            return user;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from shop_user";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<User> users = new ArrayList<>();

            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmployee(rs.getBoolean("is_employee"));
                users.add(user);
            }
            return users;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from shop_user where user_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();
            rs.next();

            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("pass"));
            user.setEmployee(rs.getBoolean("is_employee"));
            return user;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update shop_user set first_name=?, last_name=?, username=?, pass=?, is_employee=? where user_id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,user.getFirstName());
            pst.setString(2,user.getLastName());
            pst.setString(3,user.getUsername());
            pst.setString(4,user.getPassword());
            pst.setBoolean(5,user.isEmployee());
            pst.setInt(6,user.getUserId());

            pst.executeUpdate();
            return user;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
