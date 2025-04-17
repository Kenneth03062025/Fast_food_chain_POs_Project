package com.app.console;

import com.app.controller.OrderController;
import com.app.model.OrderItem;
import com.app.model.Response;
import com.app.model.dto.ListOfOrdersResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderConsole {

    public static void creatOrder(){
        ArrayList<OrderItem> ordItems = new ArrayList<>();
        OrderItem itm2 = new OrderItem("ITM-1003",5,120);
        OrderItem itm3 = new OrderItem("ITM-1004",3,360.50);
        OrderItem itm4 = new OrderItem("ITM-1005",1,450);
        //ordItems.add(itm);
        ordItems.add(itm2);
        ordItems.add(itm3);
        ordItems.add(itm4);
        Response<?> res = OrderController.createOrder(ordItems,"CSH-1001");
        System.out.println(res.getDataString());
    }

    public static void getUnpaidOrders(){
        ListOfOrdersResponse res = OrderController.getUnpaidOrders("CSH-1001");
        System.out.println(res.getOrders());
    }
}
