package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.entities.User;
import com.revature.exceptions.ResourceNotFound;
import com.revature.services.UserService;
import io.javalin.http.Handler;

import java.util.List;


public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    public Handler getAllUsers = ctx -> {
        String name = ctx.queryParam("namecontains");
        List<User> users;
        if(name != null){
            users = this.userService.getAllUsers() ;
        }else{
            int userId = 0;
            users = (List<User>) this.userService.retrieveUserById(userId);
        }
        Gson gson = new Gson();
        String usersJSON = gson.toJson(users);
        ctx.result(usersJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };

    public Handler getUserById = ctx -> {
        try{
            int userId = Integer.parseInt(ctx.pathParam("userId"));
            User user = this.userService.retrieveUserById(userId);
            Gson gson = new Gson();
            String userJSON = gson.toJson(user);
            ctx.result(userJSON);
            ctx.status(200);

        }catch (ResourceNotFound resourceNotFound){
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }

    };

    public Handler addUser = ctx -> {
        Gson gson = new Gson();
        User user = gson.fromJson(ctx.body(),User.class);
        user = this.userService.addUser(user);
        String userJSON = gson.toJson(user);
        ctx.result(userJSON);
        ctx.status(201);
    };

    public Handler updateUser = ctx -> {
        Gson gson = new Gson();
        User user = gson.fromJson(ctx.body(),User.class);
        user = this.userService.updateUser(user);
        String userJSON = gson.toJson(user);
        ctx.result(userJSON);
        ctx.status(201);
    };

    public Handler loginUser = ctx -> {
        Gson gson = new Gson();
        User user = gson.fromJson(ctx.body(),User.class);
        user = this.userService.loginUser(user.getUsername(), user.getPassword());
        String userJSON = gson.toJson(user);
        ctx.result(userJSON);
        ctx.status(201);
    };

}
