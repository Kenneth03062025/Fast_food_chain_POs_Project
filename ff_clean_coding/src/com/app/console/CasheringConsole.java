package com.app.console;

import java.util.Scanner;

public class CasheringConsole {
    private final static Scanner sc = new Scanner(System.in);

    public static void getOpenCashering(){}

    public static void displayCasheringDashboard(){
        System.out.println("What do you want? ");
        System.out.println("    [1] Create New Cashering");
        System.out.println("    [2] Update Cashering");
        System.out.println("    [3] View Stock");
        System.out.println("    [4] Back to dashboard");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();

//        switch (choice) {
//            case "1":
//                // Create new cashering
//                //w.inputCashering();
//                break;
//            case "2":
//                //Update Cashering
//                //e.inputUpdateCashering();
//                break;
//            case "3":
//                // View Stock
//                //r.inputViewStockCashering();
//                break;
//            case "4":
//                //casheringDashboardBack();
//                break;
//            default:
//                System.out.println("Invalid!");
//                casheringDashboard();
//        }
    }
}
