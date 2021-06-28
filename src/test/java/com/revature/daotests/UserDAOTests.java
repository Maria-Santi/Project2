package com.revature.daotests;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDaoLocal;
import com.revature.entities.User;
import com.revature.services.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {
    //UserDAO  userDAO = Mockito.mock(UserDAO.class);
    UserDAO userDAO = new UserDaoLocal();


    @BeforeClass
    static void setup(){
        System.out.println("BEFORE CLASS");

    }

//    @BeforeMethod
//    public void init(){
//        List<User> testUsers = new ArrayList<>();
//    //    User user1 = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
//    //    User user2 = new User(0, "Mark", "Will", "marky", "CDEF", false);
//    //    User user3 = new User(0, "David", "Bordoux", "davidB", "AB12", true);
//        User user = new User(0, "Maria", "Rose", "maryR", "BCD456",false);
//        Mockito.when(this.userDAO.getAllUsers()).thenReturn(testUsers);
//
//
//    }

    static User testUser = new User(0, "Jordan", "Pallette", "jordanP", "Abc123", true);

    @Test(priority = 1)
    void createUser(){
        User user = userDAO.createUser(testUser);
        Assert.assertNotEquals(user.getUserId(),0);

    }

    @Test(priority = 2)
    void getUserById(){
        User user = userDAO.getUserById(testUser.getUserId());
        Assert.assertEquals(user.getFirstName(), "Jordan");
    }
    @Test(priority = 3)
    void updateUser(){
        testUser.setEmployee(false);
        userDAO.updateUser(testUser);
        User user = userDAO.getUserById(testUser.getUserId());
        Assert.assertFalse(user.isEmployee());
    }

    @Test(priority = 5, dependsOnMethods = "createUser") //Intergration test
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
    @Test(priority = 6)
    void CheckUserNameExist(){
        String username ="Basdanso";
        User user = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        userDAO.createUser(user);
        boolean exist = userDAO.equals(username);
        Assert.assertFalse(exist);

    }

    @Test(priority = 7)
    void CheckPasswordExist(){
        String password ="ABCD";
        User user = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        userDAO.createUser(user);
        boolean exist = userDAO.equals(password);
        Assert.assertFalse(exist);


    }


    @AfterClass
    static void teardown(){
        System.out.println("AFTER CLASS");

    }
}
