package com.app.service.interfaces;


import com.app.model.Operation;
import com.app.model.Response;

public interface OperationService {
    Response<?> createOperation();

    Response<?> openCashering(String operationNumber);

    Response<?> closeCashering(String operationNumber);
}
