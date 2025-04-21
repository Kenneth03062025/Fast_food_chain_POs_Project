package com.app.console;

import com.app.controller.CasheringController;
import com.app.model.dto.CasheringItemResponse;
import com.app.state.AppState;

import java.util.Scanner;

public class DashBoard {

    static Scanner sc = new Scanner(System.in);

    static final String reset = "\033[0m";
    static final String bold = "\033[1m";
    static final String underline = "\033[4m";
    static final String red = "\033[31m";
    static final String green = "\033[32m";
    static final String yellow = "\033[33m";
    static final String blue = "\033[34m";
    static final String cyan = "\033[36m";
    static final String magenta = "\033[35m";

    public static void init(){
        displayDashboard();
//        CasheringItemResponse item = CasheringController.getCreatedCashering();
//        System.out.println(item.getStatus());
//
//        if(item.getStatus().equals("success")){
//            //
//            if(item.getCashering().getOperationNumber() == null){
//                displayCasheringDashboard();
//            }
//            currentCasheringNumber = item.getCashering().getOperationNumber();
//            currentCashering = item.getCashering();
//            AppState.cashering = item.getCashering();
//
//            displayCreatedCashering();
//        } else {
//            //
//            displayCasheringDashboard();
//        }
    }

    public static void displayDashboard(){
        int  selectedNumber;

        System.out.println(blue + "What do you want?");
        System.out.println(blue +"    [1] Items");
        System.out.println(blue +"    [2] Casherings");
        System.out.println(blue +"    [3] Orders");
        System.out.println(blue +"    [4] Logout");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        switch(selectedNumber){
            case 1 : ItemConsole.getAllItem();
            case 2 :
                System.out.println("Cashering");
            case 3 :
                System.out.println("Orders");
            case 4 :
                System.out.println("Payments");
            default:
                System.out.println("Invalid Input");
        }
        sc.close();
    }
}
