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


        CasheringItemResponse item = CasheringController.getCreatedCashering();
//        System.out.println(item.getStatus());
//        System.out.println(item.getCashering().getOperationNumber());
//        System.out.println(item.getCashering().getCloseAt());

        AppState.cashering = item.getCashering();
        System.out.println(AppState.user.getUserNumber() + " from App State");
        System.out.println(AppState.user.getRole() + " from App State");

        if(AppState.cashering.getOpenAt() == null){
            displayUnopenCasheringDashbord();
        } else {
            displayUnopenCasheringDashbord();
        }

//        if(item.getStatus().equals("success")){
//            //
//
//            AppState.cashering = item.getCashering();
//
//
//        } else {
//            //
//            displayCasheringDashboard();
//        }

        displayUnopenCasheringDashbord();

    }



   /* public static void displayDashboard(){
        int  selectedNumber;

        System.out.println(blue + "What do you want?");
        System.out.println(blue +"    [1] Items");
        System.out.println(blue +"    [2] Casherings");
        System.out.println(blue +"    [3] Orders");
        System.out.println(blue +"    [4] Logout");
        System.out.println( blue +"select a number from the list");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        switch(selectedNumber){
            case 1 : ItemConsole.getAllItem();
                break;
            case 2 :
                System.out.println("Cashering");
                CasheringConsole.init();
                break;
            case 3 :
                System.out.println("Orders");
                OrderConsole.init();
                break;
            case 4:
                logout();

                break;
            default:
                System.out.println("Invalid Input");
        }

    }*/

    public static void displayUnopenCasheringDashbord(){
        int  selectedNumber;
        final String reset = "\033[0m";
        final String bold = "\033[1m";
        final String underline = "\033[4m";
        final String red = "\033[31m";
        final String green = "\033[32m";
        final String yellow = "\033[33m";
        final String blue = "\033[34m";
        final String cyan = "\033[36m";
        final String magenta = "\033[35m";


        System.out.print("\033[H\033[2J");
        System.out.flush();



        System.out.println(blue+ "*===================================================*" + reset);

        System.out.println(bold +
                blue + "*   W   W " + green + " EEEEE " + red + " L     " + cyan + " CCCC  " + magenta + "OOO  " + cyan + " M   M " + red + " EEEEE"+blue+"   *" + reset);
        System.out.println(bold +
                blue + "*   W   W " + green + " E     " + red + " L     " + cyan + "C     " + magenta + "O   O " + cyan + " MM MM " + red + " E       "+blue+"*" + reset);
        System.out.println(bold +
                blue + "*   W W W " + green + " EEEE  " + red + " L     " + cyan + "C     " + magenta + "O   O " + cyan + " M M M " + red + " EEEE    "+blue+"*" + reset);
        System.out.println(bold +
                blue + "*   WW WW " + green + " E     " + red + " L     " + cyan + "C     " + magenta + "O   O " + cyan + " M   M " + red + " E       "+blue+"*" + reset);
        System.out.println(bold +
                blue + "*   W   W " + green + " EEEEE " + red + " LLLLL " + cyan + " CCCC " + magenta + " OOO  " + cyan + " M   M " + red + " EEEEE   "+blue+"*" + reset);
        System.out.println(blue+"*===================================================*");
        System.out.println(blue+"*                     Dashboard                     *");
        System.out.println(blue+"*===================================================*");
        System.out.println(blue+"*    [1] Items                                      *");
        System.out.println(blue+"*    [2] Cashiering                                 *");
        System.out.println(blue+"*    [3] "+red+"Logout"+blue+"                                     *");
        System.out.println(blue+"*===================================================*");
        System.out.println(blue+"Enter your choice: ");
        selectedNumber = sc.nextInt();
;
        sc.nextLine();

        switch(selectedNumber){
            case 1 : ItemConsole.getAllItem();
                break;
            case 2 :
                System.out.println("Cashiering");
                CasheringConsole.init();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    private static void logout() {
        System.out.println();
        System.out.println(blue + "*===================================================*");
        System.out.println(blue+  "*         Are you sure you want to logout?          *");
        System.out.println(blue+  "*===================================================*" + reset);
        System.out.println(blue+  "*   [y] YES                                         *");
        System.out.println(blue+"*"+red+"   [N] NO                                          "+blue+"*");
        System.out.println(blue + "*===================================================*");
        System.out.println(blue+"Enter your choice: ");
        String accountSignOff = sc.nextLine();


        if (accountSignOff.equalsIgnoreCase("Yes") || accountSignOff.equalsIgnoreCase("y")) {

//            sc.close();
            AuthConsole.displayAuthMain();
        }
        if (accountSignOff.equalsIgnoreCase("No") || accountSignOff.equalsIgnoreCase("n")) {
            displayUnopenCasheringDashbord();
        } else {
            System.out.println("Invalid!");
            logout();
        }
    }
}
