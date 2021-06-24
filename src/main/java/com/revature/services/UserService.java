package com.revature.services;

import com.revature.entities.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User retrieveUserById(int userId);

    List<User> getAllUsers();

    User updateUser(User user);
}
