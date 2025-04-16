package com.app.controller;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.implementations.AuthServiceImpl;

public class AuthController {

    static AuthServiceImpl aImpl = new AuthServiceImpl();

    public static Response<?> createUser(User newUser){
        Response<?> res = new Response<>();
        res = aImpl.createUserAccount(newUser);
        return res;
    }

    public static Response<User> logInUser(User logInUser){
        Response<User> res = aImpl.logInUserAccount(logInUser);
        System.out.println(res.getStatus());
//        System.out.println(res.getData());
//        res.getData();
        return res;
    }
}
