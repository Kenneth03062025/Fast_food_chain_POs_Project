package com.app.console;

import java.util.Scanner;

public class DashBoard {

    static Scanner sc = new Scanner(System.in);

    public static void displayDashboard(){
        int  selectedNumber;

        System.out.println("-------------Main Menu------------");
        System.out.println("");
        System.out.println( " [ 1 ] " + "Items");
        System.out.println(" [ 2 ] " + "Cashering");
        System.out.println(" [ 3 ] " + "Orders");
        System.out.println(" [ 4 ] " + "Payments");
        System.out.println(" [ 5 ] " + "LogOut");
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
