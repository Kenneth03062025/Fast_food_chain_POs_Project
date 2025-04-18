package com.app.console;

import com.app.controller.CasheringController;
import com.app.controller.ItemController;
import com.app.controller.StockController;
import com.app.model.Cashering;
import com.app.model.Item;
import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.CasheringItemResponse;
import com.app.model.dto.ListOfItemsResponse;
import com.app.model.dto.ListOfStocksResponse;
import com.app.state.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasheringConsole {
    private final static Scanner sc = new Scanner(System.in);

    private static List<Item> activeItems = new ArrayList<>();

    private static List<Stocks> casheringStocks = new ArrayList<>();

    private static String currentCasheringNumber;

    private static Cashering currentCashering;

    public static void init(){
        getCreatedCashering();
    }

    public static void getCasheringStocks(){
        ListOfStocksResponse res = StockController.getStocksbyCashering(currentCasheringNumber);

        if (res.getStatus().equals("success")) {
            casheringStocks = res.getStocks();
            AppState.stocksList = casheringStocks;
//            displayAllItem();
            displayStocks();
        } else {
            displayErrorConsole();
        }

//        StockController.getStocksbyCashering(currentCasheringNumber);
    }

    public static void getCreatedCashering(){
        CasheringItemResponse item = CasheringController.getCreatedCashering();
        System.out.println(item.getStatus());

        if(item.getStatus().equals("success")){
            //
            if(item.getCashering().getOperationNumber() == null){
                displayCasheringDashboard();
            }
            currentCasheringNumber = item.getCashering().getOperationNumber();
            currentCashering = item.getCashering();
            AppState.cashering = item.getCashering();

            displayCreatedCashering();
        } else {
            //
            displayCasheringDashboard();
        }
    }

    public static void getActiveItems(){
        ListOfItemsResponse res = ItemController.getActiveItems();
        if (res.getStatus().equals("success")) {
            activeItems = res.getItems();
            //displayAllItem();
            displayActiveItems();

        } else {
            displayErrorConsole();
        }
    }

    public static void addItemsToCashering(){
        List<Stocks> stocksList = new ArrayList<>();
//        CSH-1009
        Stocks stocks1 = new Stocks("CSH-1009","ITM-1021",20);
        Stocks stocks2 = new Stocks("CSH-1009","ITM-1024",10);
        stocksList.add(stocks1);
        stocksList.add(stocks2);
        Response<Stocks> res = StockController.addItemsToCashering(stocksList);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
    }
    public static void createCashering(){
        Response<Cashering> res = CasheringController.createCashering();
        if(res.getStatus().equals("success")){
            displayCasheringOptions();
        } else {
            displayErrorConsole();
            displayCasheringDashboard();
        }
    }

    public static void openCashering(){
        Response<Cashering> res = CasheringController.openCashering(currentCasheringNumber);
        if(res.getStatus().equals("success")){
            getCreatedCashering();
        } else {
            displayErrorConsole();
            displayCasheringOptions();
        }
    }

    public static void closeCashering(){
        Response<Cashering> res = CasheringController.closeCashering(currentCasheringNumber);
        if(res.getStatus().equals("success")){
            displayCasheringDashboard();
        } else {
            displayErrorConsole();
            displayCasheringOptions();
        }
    }

    public static void displayErrorConsole(){
        System.out.println("Something went wrong");
    }

    public static void displayCasheringDashboard(){
        System.out.println("What do you want? ");
        System.out.println("    [1] Create New Cashering");
        System.out.println("    [2] Back to dashboard");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice==1){
            createCashering();
        } else if (choice == 2) {
            //back
        } else {
            System.out.println("Invalid");
            displayCasheringDashboard();
        }

    }

    public static void displayCreatedCashering(){
        System.out.println("What do you want? ");
        System.out.println("    [1] " + currentCasheringNumber);
        System.out.println("    [2] Back to dashboard");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice==1){
            displayCasheringOptions();
        } else if (choice == 2) {
            //back
            System.out.println("Back to Dashboard");
        } else {
            System.out.println("Invalid");
            displayCreatedCashering();
        }

    }

    public static void displayCasheringOptions(){
        System.out.println("  " + currentCasheringNumber + "  ");
        if(currentCashering.getOpenAt() == null){
             System.out.print("Created but not yet Open");
        } else {
            System.out.print("Created and Open");
        }

        System.out.println("\n");
        System.out.println("    [1] View Stocks");

        if(currentCashering.getOpenAt() == null) {
            System.out.println("    [2] Open Cashering");
        } else {
            System.out.println("    [2] Close Cashering");
        }
        System.out.println("    [3] Back");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                System.out.println("Go to View Stocks");
                getCasheringStocks();
                break;
            case 2:
                displayConfirmatoryAction();
                break;
            case 3:
                displayCreatedCashering();
                break;
            default:
                System.out.println("Invalid");
                displayCasheringDashboard();
        }


    }

    public static void displayConfirmatoryAction(){
        System.out.println("Do you to ");
        if(currentCashering.getOpenAt() == null){
            System.out.print("Open Cashering");
        }
        else {
            System.out.print("Close Cashering");
        }

        System.out.println("    [1] Yes");
        System.out.println("    [2] No");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                if(currentCashering.getOpenAt() == null){
                    openCashering();
                }
                else {
                    closeCashering();
                }
                break;
            case 2:
                displayCasheringOptions();
                break;
            default:
                System.out.println("Invalid Input");
                displayCasheringOptions();
        }
    }

    public static void displayStocks(){
        System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", "Id", "Item Number", "Item Name","Quantity", "Items Sold");
        for ( Stocks row: casheringStocks ) {
            System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", row.getId(), row.getItemNumber(), row.getItemName(), row.getQuantity(), row.getItemSold());
        }

    }

    public static void displayActiveItems() {
        int selectedNumber;

        for ( Item itm: activeItems ) {
            System.out.println(" [" + (activeItems.indexOf(itm) + 1) + "] " + itm.getItem_no() + " " + itm.getItem_name());
        }
//        System.out.println( " [" + (items.size() + 1) + "] " + "Create New Item");
//        System.out.println(" [" + (items.size() + 2) + "] " + "Exit");
        System.out.print("Select an option: ");
        selectedNumber = sc.nextInt();
        sc.nextLine();

//        if(selectedNumber <= 0 || selectedNumber > items.size() + 2){
//            System.out.println("Invalid Input");
//            return;
//        }
//
//        if(selectedNumber < items.size() + 1){
//            selectedItem = items.get(selectedNumber-1);
//            displayAnItemOptions();
//            return;
//        }
//
//        if(selectedNumber == items.size() + 1){
//            displayCreateForm();
//            return;
//        }
//
//        if(selectedNumber == items.size() + 2 ){
//            System.out.println("Exit");
//            return;
//        }
    }
}
