package com.revature.daos;

import com.revature.entities.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    User updateUser(User user);

    User loginUser(String username, String password);

}
