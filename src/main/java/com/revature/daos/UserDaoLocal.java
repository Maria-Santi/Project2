package com.revature.daos;

import com.revature.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoLocal implements UserDAO {
    private static Map<Integer, User> userTable = new HashMap<>();
    private static int idMaker = 0;

    @Override
    public User createUser(User user) {
        int key = ++UserDaoLocal.idMaker;
        user.setUserId(key);
        UserDaoLocal.userTable.put(key, user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return UserDaoLocal.userTable.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(UserDaoLocal.userTable.values());
        return users;
    }

    @Override
    public User updateUser(User user) {
        UserDaoLocal.userTable.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User loginUser(String username, String password) {
        return null;
    }

//    @Override
//    public User loginUser(String username, String password) {
//        UserDaoLocal.userTable.get(username.compareTo("username"));
//        UserDaoLocal.userTable.get(password.compareTo("password"));
//        if (username.contentEquals(username) && password.contentEquals(password)){
//            System.out.println("Valid Username or password");
//        } else {
//            System.out.println("User found");
//
//        }
//        return  User;
//
//    }




}
