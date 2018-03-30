package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_QUEUE;
import static com.tpg.pjs.config.ActiveMQConfig.ORDER_STATUS_QUEUE;

@Component
public class OrderSender implements OrderAcceptance {

    @Autowired
    public OrderSender(JmsTemplate ordersJmsTemplate, JmsTemplate ordersStatusJmsTemplate) {

        this(ordersJmsTemplate, ordersStatusJmsTemplate, null);
    }

    OrderSender(JmsOperations ordersQueue, JmsOperations ordersStatusQueue, MessageCreator messageCreator) {

        this.ordersQueue = ordersQueue;

        this.ordersStatusQueue = ordersStatusQueue;

        this.messageCreator = messageCreator;
    }

    @Override
    public void placeOnQueue(Order order) {

        ordersQueue.send(ORDER_QUEUE, messageCreator);

        LOG.info("Received order {}/{}", order.getUserId(), order.getSessionId());

        ordersStatusQueue.send(ORDER_STATUS_QUEUE, messageCreator);
    }

    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);

    private final JmsOperations ordersQueue;
    private final JmsOperations ordersStatusQueue;
    private final MessageCreator messageCreator;
}
