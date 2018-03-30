package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;

public interface OrderDeliverance {

    void receiveMessage(Order order);
}
