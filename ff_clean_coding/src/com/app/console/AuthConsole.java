package com.app.console;

import com.app.controller.AuthController;
import com.app.model.Response;
import com.app.model.User;

import java.util.Scanner;

public class AuthConsole {

    static Scanner sc = new Scanner(System.in);

    public static void getLogInUser(User logInUser){
        Response<User> res = AuthController.logInUser(logInUser);
        if(res.getStatus().equals("success")){
            //System.out.println(res.getData());
            System.out.println("Dashboard");
        } else{
            displayAuthFailed(res.getStatus(), res.getMessage());
        }
    }

    public static void createNewUser(User newUser){
        Response<?> res = AuthController.createUser(newUser);
        if(res.getStatus().equals("success")){
            System.out.println(res.getDataString());
            System.out.println("LogIn");
            displayLogInForm();
        } else{
            displayAuthFailed(res.getStatus(), res.getMessage());
        }
    }

    public static void displayCreateUserForm(){
        System.out.println("\nCreate account");
        System.out.print("Enter your firstname: ");
        String firstName = sc.nextLine();
        System.out.print("Enter your lastname: ");
        String lastName = sc.nextLine();
        System.out.print("Enter your middle name: ");
        String middleName = sc.nextLine();
        System.out.print("Enter your username: ");
        String userName = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = new User(firstName,lastName,middleName,userName,password);
        createNewUser(user);

    }

    public static void displayLogInForm(){
        System.out.print("Enter your username: ");
        String userLoginName = sc.nextLine();
        System.out.print("Enter your password: ");
        String userLoginPassword = sc.nextLine();

        User user = new User(userLoginName,userLoginPassword);
        getLogInUser(user);
    }

    public static void displayAuthFailed(String status, String message){
        System.out.println(status);
        System.out.println(message);

        displayLogInForm();
    }
}
