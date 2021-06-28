package com.revature.servicetests;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDaoLocal;
import com.revature.entities.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTests {
 //   UserDAO userDAO = Mockito.mock(UserDAO.class);
    UserDAO userDAO = new UserDaoLocal();
    UserService userService = new UserServiceImpl(userDAO);
//
//    @BeforeMethod
//    public void init(){
//
//        List<User> testUsers = new ArrayList<>();
//        User user1 = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
//        User user2 = new User(0, "Mark", "Will", "marky", "CDEF", false);
//        User user3 = new User(0, "David", "Bordoux", "davidB", "AB12", true);
//        User user4 = new User(0, "Maria", "Rose", "maryR", "BCD456",false);
//        testUsers.add(user1);
//        testUsers.add(user2);
//        testUsers.add(user3);
//        testUsers.add(user4);
//    //    Mockito.when(this.userDAO.getAllUsers()).thenReturn(testUsers);
//
//    }

    @Test
    void retrieveUserById(){
        User user =  this.userService.retrieveUserById(1);
        Assert.assertEquals(user.getUserId(), 1);

    }

    @Test
    void getAllUsers(){

        User user1 = new User(0, "Basiru", "Danso", "Basdanso", "ABCD", true);
        User user2 = new User(0, "Mark", "Will", "marky", "CDEF", false);
        User user3 = new User(0, "David", "Bordoux", "davidB", "AB12", true);
        User user4 = new User(0, "Maria", "Rose", "maryR", "BCD456",false);
        this.userService.addUser(user1);
        this.userService.addUser(user2);
        this.userService.addUser(user3);
        this.userService.addUser(user4);
        List<User> users = this.userService.getAllUsers();
        Assert.assertTrue(users.size()>= 4);
        Assert.assertNotEquals(users.get(0).getUserId(), 0);
    }


}
