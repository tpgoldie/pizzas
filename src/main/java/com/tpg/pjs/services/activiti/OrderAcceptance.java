package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;

public interface OrderAcceptance {

    void placeOnQueue(Order order);
}
