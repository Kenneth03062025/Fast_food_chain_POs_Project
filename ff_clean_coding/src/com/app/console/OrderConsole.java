package com.app.console;

import com.app.controller.OrderController;
import com.app.controller.OrderItemController;
import com.app.controller.PaymentController;
import com.app.controller.StockController;
import com.app.model.*;
import com.app.model.dto.ListOfItemsAvailableResponse;
import com.app.model.dto.ListOfOrderItemsResponse;
import com.app.model.dto.ListOfOrdersResponse;
import com.app.model.dto.PaymentResponse;
import com.app.state.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {

    static List<Order> orderList;

    static String selectedOrderNumber;

    static Order selectedOrder;

    static OrderItem orderItems;

    static List<OrderItem> orderItemList = new ArrayList<>();

    static List<ItemAvailable> availables;

    private static Scanner sc = new Scanner(System.in);

    public static void init(){
        getAvailableStocks();
    }

    public static void tryAgain(){
        System.out.println("Would You Like to Try Again");

        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            System.out.println("Thank you for using our application");
            displayOrderOptions();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            dispose();
            System.out.println("Failed Exit");
        } else {
            System.out.println("Invalid! Input the Yes or No");
            dispose();
        }
    }

    public static void creatOrder(){
        ArrayList<OrderItem> ordItems = new ArrayList<>();
        OrderItem itm2 = new OrderItem("ITM-1020",2,145);
        OrderItem itm3 = new OrderItem("ITM-1021",1,50);
        OrderItem itm4 = new OrderItem("ITM-1024",1,95);
        //ordItems.add(itm);
        ordItems.add(itm2);
        ordItems.add(itm3);
        ordItems.add(itm4);
        PaymentResponse res = PaymentController.placeOrderPayment(ordItems,"CSH-1012");
//        Response<?> res = OrderController.createOrder(ordItems,"CSH-1001");
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());

//        ArrayList<String> itemList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        int choice;

//        do {
//            System.out.println("\n=== ArrayList CRUD Menu ===");
//            System.out.println("1. Create (Add Item)");
//            System.out.println("2. Read (View Items)");
//            System.out.println("3. Update Item");
//            System.out.println("4. Delete Item");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice (1-5): ");
//            choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline
//
//            switch (choice) {
//                case 1: // Create
//                    System.out.print("Enter item to add: ");
//                    String newItem = scanner.nextLine();
//                    itemList.add(newItem);
//                    System.out.println("Item added successfully.");
//                    break;
//
//                case 2: // Read
//                    System.out.println("Current items in list:");
//                    if (itemList.isEmpty()) {
//                        System.out.println("[No items in the list]");
//                    } else {
//                        for (int i = 0; i < itemList.size(); i++) {
//                            System.out.println(i + ": " + itemList.get(i));
//                        }
//                    }
//                    break;
//
//                case 3: // Update
//                    System.out.print("Enter index of item to update: ");
//                    int updateIndex = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline
//                    if (updateIndex >= 0 && updateIndex < itemList.size()) {
//                        System.out.print("Enter new value: ");
//                        String updatedItem = scanner.nextLine();
//                        itemList.set(updateIndex, updatedItem);
//                        System.out.println("Item updated.");
//                    } else {
//                        System.out.println("Invalid index.");
//                    }
//                    break;
//
//                case 4: // Delete
//                    System.out.print("Enter index of item to delete: ");
//                    int deleteIndex = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline
//                    if (deleteIndex >= 0 && deleteIndex < itemList.size()) {
//                        itemList.remove(deleteIndex);
//                        System.out.println("Item deleted.");
//                    } else {
//                        System.out.println("Invalid index.");
//                    }
//                    break;
//
//                case 5: // Exit
//                    System.out.println("Exiting program...");
//                    break;
//
//                default:
//                    System.out.println("Invalid choice! Please try again.");
//            }
//
//        } while (choice != 5);

    }

    public static void addItemToList(OrderItem item){
        orderItemList.add(item);
    }

    public static void removeItem(int orderIndex){
        orderItemList.remove(orderIndex);
    }

    public static void changeQuantity(int index, OrderItem item){
        orderItemList.set(index,item);
    }


    public static void getAvailableStocks(){
        ListOfItemsAvailableResponse res = StockController.getAvailableItems("CSH-1012");
        if(res.getStatus().equals("success")){
            availables = res.getItemsAvailable();
            displayOrderOptions();
        } else {
            System.out.println("Error");
        }
    }

//    public static void getOrderItems(){
//
//        ListOfOrderItemsResponse res = OrderItemController.showOrderItems(selectedOrder.getOrderNumber());
//
//        orderItemList = res.getOrderItems();
//
//        displayOrderItems();
//    }
//
//    public static void displayUnpaidOrders(){
//        int selectedNumber;
//
//        for ( Order itm: orderList ) {
//            System.out.println(" [" + (orderList.indexOf(itm) + 1) + "] " + itm.getOrderNumber() );
//        }
//        System.out.println( " [" + (orderList.size() + 1) + "] " + "Create Order");
//        System.out.println(" [" + (orderList.size() + 2) + "] " + "Exit");
//        System.out.print("Select an option: ");
//        selectedNumber = sc.nextInt();
//        sc.nextLine();
//////        sc.nextLine();
//
//        if(selectedNumber <= 0 || selectedNumber > orderList.size() + 2){
//            System.out.println("Invalid Input");
//            return;
//        }
//
//        if(selectedNumber < orderList.size() + 1){
//            selectedOrder = orderList.get(selectedNumber-1);
//            getOrderItems();
//            return;
//        }
//
//        if(selectedNumber == orderList.size() + 1){
////            displayCreateForm();
//            System.out.println("Create New Order");
//            return;
//        }
//
//        if(selectedNumber == orderList.size() + 2 ){
//            System.out.println("Exit");
//            return;
//        }
//    }


//    public static void displayOrderItems(){
//        System.out.println(selectedOrder.getOrderNumber() +"    -----------   " + selectedOrder.getStatus());
//        double orderTotal = 0;
//
//
//
//        for (OrderItem oi: orderItemList) {
//            System.out.println(oi.getItemNumber()+ "   " + oi.getItemName()+ "   " + oi.getQuantity() + " @  " + oi.getPrice()+ "  =  " + oi.getItemTotal());
//            orderTotal = orderTotal + oi.getItemTotal();
//
//        }
//
////        System.out.println("ITM-1011" + " Sisig Meal" + "5 @ 95 = 475");
////        System.out.println("ITM-1011" + " Sisig Meal" + "5 @ 95 = 475");
////        System.out.println("ITM-1011" + " Sisig Meal" + "5 @ 95 = 475");
//
//        System.out.println("Order Total: " + orderTotal);
////        System.out.println();
//    }

    public static void displayAvailableStocks(){
        System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", "Id", "Item Number", "Item Name","Price", "Items Sold");
        if(availables.size() > 0) {
            for (ItemAvailable row : availables) {
                System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", availables.indexOf(row)+1 , row.getItemNumber(), row.getItemName(), row.getPrice() ,row.getAvailable());
            }

        } else {
            System.out.println("No Available Stocks");
        }
    }

    public static void displayOrderOptions(){
        displayAvailableStocks();
        System.out.println("  ");
        System.out.println("  ");
        System.out.println(" [ 1. ]  Create Order");
        System.out.println(" [ 2. ]  Exit");

        System.out.println("Enter Your Choice");


            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: //create order
                    displayTemporaryList();
                    break;
                case 2:
                    dispose();
                    break;
                case 3:
                    System.out.println("Invalid Input");
                    displayOrderOptions();
            }





    }


    public static void displayTemporaryList(){

        if(orderItemList.size() > 0) {
            System.out.println("Your Item Selected");
            System.out.println("");
            System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", " ", "Item Number", "Item Name","Quantity", "Items Sold");
            for (OrderItem row : orderItemList) {
                System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", orderItemList.indexOf(row), row.getItemNumber(), row.getItemName(), row.getPrice());
            }
        } else {
            System.out.println("No Items on This List");
        }
        displayAvailableStocks();
        System.out.println("Select Item to Add to List: ");
        int choice = sc.nextInt();
        sc.nextLine();
        OrderItem item = new OrderItem();

//        addItemToList();
    }

    public static void dispose(){
        //close the scanner
        sc.close();
        DashBoard.init();
    }
























    //    public static void getUnpaidOrders(){
//        ListOfOrdersResponse res = OrderController.getUnpaidOrders(AppState.cashering.getOperationNumber());
//
//        if(res.getStatus().equals("success")){
//            orderList = res.getOrders();
//            displayUnpaidOrders();
//        } else {
//            System.out.println("Error");
//        }
//    }

}
