package com.app.controller;

import com.app.model.Response;
import com.app.service.implementations.OperationServiceImpl;

public class CasheringController {

    public static void createOpration(){
        Response<?> res = new Response<>();
        OperationServiceImpl impl = new OperationServiceImpl();
        res = impl.createOperation();
        System.out.println(res.getStatus());
    }

    public static void openCashering(){
        Response<?> res = new Response<>();
        OperationServiceImpl impl = new OperationServiceImpl();
        res = impl.openCashering("OPR-1000");
        System.out.println(res.getStatus());
    }

    public static void closeCashering(){
        Response<?> res = new Response<>();
        OperationServiceImpl impl = new OperationServiceImpl();
        res = impl.closeCashering("OPR-1000");
        System.out.println(res.getStatus());
    }
}
