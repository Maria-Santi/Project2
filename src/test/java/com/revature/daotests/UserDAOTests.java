package com.revature.daotests;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDaoPostgres;
import com.revature.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserDAOTests {

    static UserDAO userDAO = new UserDaoPostgres();
    static User testUser = new User(0, "Donald", "Duck", "donduck", "pass", false);


    @Test(priority = 1)
    void createUser(){
        User user = userDAO.createUser(testUser);
        Assert.assertNotEquals(user.getUserId(),0);
    }

    @Test(priority = 2)
    void getUserById(){
        User user = userDAO.getUserById(testUser.getUserId());
        Assert.assertEquals(user.getFirstName(), "Donald");
    }

    @Test(priority = 3)
    void updateUser(){
        testUser.setEmployee(true);
        userDAO.updateUser(testUser);
        User user = userDAO.getUserById(testUser.getUserId());
        Assert.assertTrue(user.isEmployee());
    }

    @Test(priority = 4, dependsOnMethods = "createUser") //Integration test
    void getAllUsers(){
        User user1 = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        User user2 = new User(0, "Mark", "Will", "marky", "CDEF", false);
        User user3 = new User(0, "David", "Bordoux", "davidB", "AB12", true);
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        List<User> users = userDAO.getAllUsers();
        Assert.assertTrue(users.size()>=3);
    }

    @Test(priority = 5)
    void CheckUserNameExist(){
        String username ="Basdanso";
        User user = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        userDAO.createUser(user);
        boolean exist = userDAO.equals(username);
        Assert.assertFalse(exist);

    }

    @Test(priority = 6)
    void CheckPasswordExist(){
        String password ="ABCD";
        User user = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        userDAO.createUser(user);
        boolean exist = userDAO.equals(password);
        Assert.assertFalse(exist);
    }
}
