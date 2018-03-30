package com.tpg.pjs.services.activiti;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;

import static com.tpg.pjs.services.activiti.PlaceOrderOnQueue.given;

public class OrderSenderTest extends OrderMessagingTest {

    @Test
    public void placeOrderOnQueue() throws JMSException {

        given()
            .jmsOperations(jmsOperations)
            .messageCreator(messageCreator)
            .action(action)
        .when()
            .placeOrderOnQueue(order)
        .then()
            .orderIsPlacedOnQueue();
    }

    @Mock
    private MessageCreator messageCreator;

    @InjectMocks
    private OrderSender action;
}
