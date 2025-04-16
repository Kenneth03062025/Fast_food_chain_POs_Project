package com.app.service.interfaces;

import com.app.model.Order;
import com.app.model.Payment;
import com.app.model.Response;

public interface PaymentService {
    Response<?> createPayment(Payment payment);
}
