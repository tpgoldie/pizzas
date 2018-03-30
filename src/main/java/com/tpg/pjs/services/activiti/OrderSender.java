package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_QUEUE;
import static com.tpg.pjs.ordering.Order.Status.ACCEPTED;

@Component
public class OrderSender implements OrderAcceptance {

    @Autowired
    public OrderSender(JmsTemplate jmsTemplate) {

        this(jmsTemplate, null);
    }

    OrderSender(JmsOperations jmsOperations, MessageCreator messageCreator) {

        this.jmsOperations = jmsOperations;

        this.messageCreator = messageCreator;
    }

    @Override
    public Status placeOnQueue(Order order) {

        jmsOperations.send(ORDER_QUEUE, messageCreator);

        LOG.info("Received order {}/{}", order.getUserId(), order.getSessionId());

        // TODO refactor to sending order/accepted via message queue back to order service
        return ACCEPTED;
    }

    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);

    private final JmsOperations jmsOperations;
    private final MessageCreator messageCreator;
}
