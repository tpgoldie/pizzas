package com.tpg.pjs.services.activiti;

import com.tpg.pjs.config.ActiveMQConfig;
import com.tpg.pjs.ordering.Order;
import lombok.Getter;
import org.springframework.jms.core.JmsOperations;

public class OrdersMessageHandler implements OrdersMessageSender {

    @Override
    public void send(Order order) {

        jmsOperations.convertAndSend(ActiveMQConfig.ORDER_QUEUE, new OrderEnvelope(order));
    }

    OrdersMessageHandler(JmsOperations jmsOperations) {

        this.jmsOperations = jmsOperations;
    }

    @Getter
    static class OrderEnvelope {

        OrderEnvelope(Order order) {

            this.order = order;
        }

        private final Order order;
    }

    private final JmsOperations jmsOperations;
}
