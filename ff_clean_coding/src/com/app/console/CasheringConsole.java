package com.app.console;

import com.app.controller.CasheringController;
import com.app.controller.ItemController;
import com.app.controller.StockController;
import com.app.model.Item;
import com.app.model.Stocks;
import com.app.model.dto.CasheringItemResponse;
import com.app.model.dto.ListOfItemsResponse;
import com.app.model.dto.ListOfStocksResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasheringConsole {
    private final static Scanner sc = new Scanner(System.in);

    private static List<Item> activeItems = new ArrayList<>();

    private static List<Stocks> casheringStocks = new ArrayList<>();

    private static String currentCasheringNumber;

    public static void getCasheringStocks(){
        ListOfStocksResponse res = StockController.getStocksbyCashering(currentCasheringNumber);

        if (res.getStatus().equals("success")) {
//            casheringStocks = res.getItems();
//            displayAllItem();
        } else {
            displayErrorConsole();
        }

        StockController.getStocksbyCashering(currentCasheringNumber);
    }

    public static void getOpenCashering(){
        CasheringItemResponse item = CasheringController.getCreatedCashering();
        if(item.getStatus().equals("success")){
            //
        } else {
            //
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


    public static void createCahering(){
        CasheringController.createCashering();
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
            //displayCreatedCashering
        } else if (choice == 2) {
            //back
        } else {
            System.out.println("Invalid");
            displayCasheringDashboard();
        }

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

    public static void displayStocks(){
        System.out.printf("\n%-5s %-15s %-10s %-10s\n", "Id", "Item Number", "Quantity", "Items Sold");
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
