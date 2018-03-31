package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
import lombok.Getter;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_STATUS_QUEUE;

public class OrdersStatusMessageHandler implements OrdersStatusMessageSender {

    @Override
    public void send(Order order, Status status) {

        jmsOperations.convertAndSend(ORDER_STATUS_QUEUE, new OrderEnvelope(order, status));
    }

    OrdersStatusMessageHandler(JmsOperations jmsOperations) {

        this.jmsOperations = jmsOperations;
    }

    @Getter
    static class OrderEnvelope {

        OrderEnvelope(Order order, Status status) {

            this.order = order;
            this.status = status;
        }

        private final Order order;
        private final Status status;
    }

    private final JmsOperations jmsOperations;
}
