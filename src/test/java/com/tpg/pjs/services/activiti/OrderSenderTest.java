package com.tpg.pjs.services.activiti;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;

import static com.tpg.pjs.services.activiti.PlaceOrderOnQueue.given;

public class OrderSenderTest extends OrderMessagingTest {

    @Before
    public void setUp() {

        super.setUp();

        orderSender = new OrderSender(ordersMessageSender, ordersStatusMessageSender, messageCreator);
    }

    @Test
    public void placeOrderOnQueue() throws JMSException {

        given()
            .ordersMessageSender(ordersMessageSender)
            .ordersStatusMessageSender(ordersStatusMessageSender)
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
    private OrdersStatusMessageSender ordersStatusMessageSender;

    private OrderSender orderSender;
}
