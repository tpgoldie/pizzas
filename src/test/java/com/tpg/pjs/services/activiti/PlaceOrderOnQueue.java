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

    PlaceOrderOnQueue ordersMessageSender(OrdersMessageSender ordersMessageSender) {

        this.ordersMessageSender = ordersMessageSender;

        return this;
    }

    PlaceOrderOnQueue ordersStatusMessageSender(OrdersStatusMessageSender ordersStatusMessageSender) {

        this.ordersStatusMessageSender = ordersStatusMessageSender;

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

        this.order = order;

        queueAction.placeOnQueue(order);

        return this;
    }

    PlaceOrderOnQueue then() {

        return this;
    }

    PlaceOrderOnQueue orderIsPlacedOnQueue() throws JMSException {

        verify(ordersMessageSender).send(order);

        return this;
    }

    PlaceOrderOnQueue orderAcceptedStatusResponseSent() {

        verify(ordersStatusMessageSender).send(order, ACCEPTED);

        return this;
    }

    private OrdersMessageSender ordersMessageSender;
    private OrdersStatusMessageSender ordersStatusMessageSender;
    private MessageCreator messageCreator;
    private OrderSender queueAction;
    private Order order;
}
