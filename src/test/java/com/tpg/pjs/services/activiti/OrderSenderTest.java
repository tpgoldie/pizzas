package com.tpg.pjs.services.activiti;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;

import static com.tpg.pjs.services.activiti.PlaceOrderOnQueue.given;

public class OrderSenderTest extends OrderMessagingTest {

    @Before
    public void setUp() {

        super.setUp();

        orderSender = new OrderSender(ordersQueue, ordersStatusQueue, messageCreator);
    }

    @Test
    public void placeOrderOnQueue() throws JMSException {

        given()
            .ordersQueue(ordersQueue)
            .ordersStatusQueue(ordersStatusQueue)
            .messageCreator(messageCreator)
            .orderSender(orderSender)
        .when()
            .placeOrderOnQueue(order)
        .then()
            .orderIsPlacedOnQueue()
            .orderAcceptedStatusResponseSent();
    }

    @Mock
    private MessageCreator messageCreator;

    @Mock
    private JmsOperations ordersStatusQueue;

    private OrderSender orderSender;
}
