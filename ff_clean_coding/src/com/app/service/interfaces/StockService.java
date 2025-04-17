package com.app.service.interfaces;

import com.app.model.dto.ListOfStocksResponse;

public interface StockService {
    ListOfStocksResponse getStocksbyCashering(String casheringNumber);
}
