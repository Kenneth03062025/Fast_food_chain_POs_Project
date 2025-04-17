package com.app.controller;

import com.app.model.Cashering;
import com.app.model.Response;
import com.app.model.dto.CasheringItemResponse;
import com.app.service.implementations.CasheringServiceImpl;

public class CasheringController {

    static CasheringServiceImpl cImpl = new CasheringServiceImpl();

    public static void createCashering(){
        Response<?> res = new Response<>();
        res = cImpl.createCashering();
        System.out.println(res.getStatus());
    }

    public static void openCashering(){
        Response<?> res = new Response<>();
        res = cImpl.openCashering("OPR-1000");
        System.out.println(res.getStatus());
    }

    public static void closeCashering(){
        Response<?> res = new Response<>();
        res = cImpl.closeCashering("OPR-1000");
    }

    public static CasheringItemResponse getCreatedCashering(){
        CasheringItemResponse res = cImpl.getOpenCashering();
        return res;
    }


}
