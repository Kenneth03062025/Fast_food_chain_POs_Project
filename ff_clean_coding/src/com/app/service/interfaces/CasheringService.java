package com.app.service.interfaces;

import com.app.model.Response;
import com.app.model.Stocks;

import java.util.List;

public interface CasheringService {

    Response<?> createCashering();

    Response<?> openCashering(String operationNumber);

    Response<?> closeCashering(String operationNumber);

    Response<?> getOpenCashering();

    Response<?> addItemsToCashering(List<Stocks> stocks);

//    Response<Stocks> getCaheringItem();
}
