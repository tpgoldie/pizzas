package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;

public interface OrdersMessageSender {

    void send(Order order);
}
