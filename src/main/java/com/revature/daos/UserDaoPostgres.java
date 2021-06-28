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
            String sql = "insert into book (firstName, lastName, username,password,isEmployee) values (?,?,?,?,?)";
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
            String sql = "select * from user";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<User> users = new ArrayList<>();

            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmployee(rs.getBoolean("isEmployee"));
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
            String sql = "select * from user where user_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();
            rs.next();

            User user = new User();
            user.setUserId(rs.getInt("userid"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmployee(rs.getBoolean("isEmployee"));
            return user;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ResourceNotFound("Resource with  id " + userId +" not found" );
        }
    }

    @Override
    public User updateUser(User user) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update user set first_name=?, last_name=?, username=?, password=?, isEmployee=? where user_id =?";
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

    @Override
    public User loginUser(String username, String password) {

        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from user where username =? and password = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {

                // Optional, method to send user to home page
//                Userhome uh = new UserHome();
//                uh.setUsername("Welcome");
//                uh.setVisible(true);

                JOptionPane.showMessageDialog(this, "Welcome" +username+ "username and password matched and you are logged in");
                User user = new User();
                user.setEmployee(true);
                this.loginUser(username, password);
            }else if(rs.next()) {
                User user = new User();
                user.setEmployee(false);
                this.loginUser(username, password);
            }else {
                JOptionPane.showMessageDialog(this, "Username and password do not match");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;

    }
}
