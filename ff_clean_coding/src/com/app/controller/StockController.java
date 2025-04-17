package com.app.controller;

import com.app.model.dto.ListOfStocksResponse;
import com.app.service.implementations.StockServiceImpl;
import com.app.service.interfaces.StockService;

public class StockController {

    static StockServiceImpl sImpl = new StockServiceImpl();
    public static ListOfStocksResponse getStocksbyCashering(String casheringNumber){
        ListOfStocksResponse res = sImpl.getStocksbyCashering(casheringNumber);
        return res;
    }
}
