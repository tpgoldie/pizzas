package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_QUEUE;
import static com.tpg.pjs.ordering.Order.Status.ACCEPTED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

class PlaceOrderOnQueue {

    static PlaceOrderOnQueue given() {

        return new PlaceOrderOnQueue();
    }

    private PlaceOrderOnQueue() {}

    PlaceOrderOnQueue jmsOperations(JmsOperations jmsOperations) {

        this.jmsOperations = jmsOperations;

        return this;
    }

    PlaceOrderOnQueue action(OrderSender queueAction) {

        this.queueAction = queueAction;

        return this;
    }

    PlaceOrderOnQueue messageCreator(MessageCreator messageCreator) {

        this.messageCreator = messageCreator;

        return this;
    }

    PlaceOrderOnQueue when() {

        return this;
    }

    PlaceOrderOnQueue then() {

        return this;
    }

    PlaceOrderOnQueue placeOrderOnQueue(Order order) {

        actual = queueAction.placeOnQueue(order);

        return this;
    }

    PlaceOrderOnQueue orderIsPlacedOnQueue() throws JMSException {

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(jmsOperations).send(argumentCaptor.capture(), eq(messageCreator));

        String actualName = argumentCaptor.getValue();

        assertEquals(ORDER_QUEUE, actualName);

        assertEquals(ACCEPTED, actual);

        return this;
    }

    private JmsOperations jmsOperations;
    private MessageCreator messageCreator;
    private OrderSender queueAction;
    private Order.Status actual;
}
