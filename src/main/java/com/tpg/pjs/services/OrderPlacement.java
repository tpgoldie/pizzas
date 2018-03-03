package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;

public interface OrderPlacement {

    OrderDetailsStatus placeOrder(Order order);
}
