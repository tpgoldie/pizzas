package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;

public interface OrderAcceptance {

    Status placeOnQueue(Order order);
}
