package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.springframework.jms.core.JmsOperations;

abstract class OrderMessaging {

    JmsOperations jmsOperations;
    Order order;
}
