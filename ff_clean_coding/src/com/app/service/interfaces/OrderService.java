package com.app.service.interfaces;

import com.app.model.OrderItem;
import com.app.model.Payment;
import com.app.model.Response;

import java.util.ArrayList;

public interface OrderService {
    Response<?> createOrder(ArrayList<OrderItem> orderItems);
}
