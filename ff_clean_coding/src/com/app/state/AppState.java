package com.app.state;

import com.app.model.Item;
import com.app.model.Order;
import com.app.model.User;

public class AppState {

    public static User user = new User();
    public static Item item = new Item();

    public static Order order = new Order();
}
