package com.app.service.interfaces;

import com.app.model.Response;

public interface CasheringService {

    Response<?> createCashering();

    Response<?> openCashering(String operationNumber);

    Response<?> closeCashering(String operationNumber);

    Response<?> getOpenCashering();
}
