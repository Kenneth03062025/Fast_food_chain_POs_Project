package com.app.controller;

import com.app.model.OrderItem;
import com.app.service.implementations.ItemServiceImpl;
import com.app.service.implementations.OrderServiceImpl;
import com.app.service.interfaces.OrderService;

import java.util.ArrayList;

public class OrderController {

    public static void createOrder(){
        ArrayList<OrderItem> ordItems = new ArrayList<>();
        OrderItem itm2 = new OrderItem("ITM-1003",5,120);
        OrderItem itm3 = new OrderItem("ITM-1004",3,360.50);
        OrderItem itm4 = new OrderItem("ITM-1005",1,450);
        //ordItems.add(itm);
        ordItems.add(itm2);
        ordItems.add(itm3);
        ordItems.add(itm4);
        OrderServiceImpl ipl = new OrderServiceImpl();
        ipl.createOrder(ordItems);

    }
}
