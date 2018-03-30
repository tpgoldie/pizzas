package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_QUEUE;
import static com.tpg.pjs.ordering.Order.Status.ACCEPTED;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

class PlaceOrderOnQueue {

    static PlaceOrderOnQueue given() {

        return new PlaceOrderOnQueue();
    }

    private PlaceOrderOnQueue() {}

    PlaceOrderOnQueue ordersQueue(JmsOperations ordersQueue) {

        this.ordersQueue = ordersQueue;

        return this;
    }

    PlaceOrderOnQueue ordersStatusQueue(JmsOperations ordersStatusQueue) {

        this.ordersStatusQueue = ordersStatusQueue;

        return this;
    }

    PlaceOrderOnQueue orderSender(OrderSender queueAction) {

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

    PlaceOrderOnQueue placeOrderOnQueue(Order order) {

        queueAction.placeOnQueue(order);

        return this;
    }

    PlaceOrderOnQueue then() {

        return this;
    }

    PlaceOrderOnQueue orderIsPlacedOnQueue() throws JMSException {

        verify(ordersQueue).send(stringArgumentCaptor.capture(), eq(messageCreator));

        String actualName = stringArgumentCaptor.getValue();

        assertEquals(ORDER_QUEUE, actualName);

        return this;
    }

    PlaceOrderOnQueue orderAcceptedStatusResponseSent() {

        verify(ordersStatusQueue).send(stringArgumentCaptor.capture(), messageCreatorArgumentCaptor.capture());

        String destination = stringArgumentCaptor.getValue();

        assertEquals(destination, "orders-status-queue");

        MessageCreator messageCreatorActual = messageCreatorArgumentCaptor.getValue();

        assertThat(messageCreatorActual, is(not(nullValue())));

        return this;
    }

    private JmsOperations ordersQueue;
    private JmsOperations ordersStatusQueue;
    private MessageCreator messageCreator;
    private OrderSender queueAction;

    private ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
    private ArgumentCaptor<MessageCreator> messageCreatorArgumentCaptor = ArgumentCaptor.forClass(MessageCreator.class);
}
