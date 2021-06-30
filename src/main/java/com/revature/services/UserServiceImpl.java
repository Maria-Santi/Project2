package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDaoPostgres;
import com.revature.entities.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDAO userDAO = new UserDaoPostgres();

    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO; // dependency injection
    }

    @Override
    public User addUser(User user) {
        return this.userDAO.createUser(user);
    }

    @Override
    public User retrieveUserById(int userId) {
        return this.userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public User updateUser(User user) {
        return this.userDAO.updateUser(user);
    }

}
