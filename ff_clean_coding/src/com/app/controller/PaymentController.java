package com.app.controller;

import com.app.model.Item;
import com.app.model.Payment;
import com.app.model.Response;
import com.app.service.implementations.ItemServiceImpl;
import com.app.service.implementations.PaymentServiceImpl;

public class PaymentController {
    public static Response<String> savePayment (Payment payment){
        Response<String> res = new Response<>();
//        Response<String> responseReturn = new Response<>();
        PaymentServiceImpl pmt = new PaymentServiceImpl();
        Response<?> serviceRes = pmt.createPayment(payment);
//        res = itm.saveItem(item);
//        System.out.println(res.getStatus());
//        System.out.println(res.getMessage());
        res.setStatus(serviceRes.getStatus());
        res.setMessage(serviceRes.getMessage());
        res.setData((String) res.getData());
        return res;
//        if(serviceRes.getStatus().equals("failed")){
//            return (Response<String>) res;
//        }
//        return (Response<String>) (res = new Response<>(res.getStatus(),res.getMessage(),(String) res.getData()));
    }
}
