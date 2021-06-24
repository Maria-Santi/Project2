package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.services.UserService;
import io.javalin.http.Handler;


public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
}
