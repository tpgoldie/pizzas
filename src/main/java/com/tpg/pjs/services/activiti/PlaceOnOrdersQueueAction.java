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

@Component
public class PlaceOnOrdersQueueAction implements OrderSender {

    @Autowired
    public PlaceOnOrdersQueueAction(JmsTemplate jmsTemplate) {

        this(jmsTemplate, null);
    }

    PlaceOnOrdersQueueAction(JmsOperations jmsOperations, MessageCreator messageCreator) {

        this.jmsOperations = jmsOperations;

        this.messageCreator = messageCreator;
    }

    @Override
    public Status placeOnQueue(Order order) {
        jmsOperations.send(ORDER_QUEUE, messageCreator);
        return null;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceOnOrdersQueueAction.class);

    private final JmsOperations jmsOperations;
    private final MessageCreator messageCreator;
}
