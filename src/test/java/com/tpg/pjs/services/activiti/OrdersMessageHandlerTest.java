package com.tpg.pjs.services.activiti;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jms.core.JmsOperations;

import static com.tpg.pjs.services.activiti.SendOrderMessage.given;

public class OrdersMessageHandlerTest extends OrderMessagingTest {

    @Test
    public void sendMessage() {

        given()
            .handler(handler)
            .jmsOperations(jmsOperations)
        .when()
            .sendOrderMessage(order)
        .then()
            .orderIsSent();
    }

    @Mock
    private JmsOperations jmsOperations;

    @InjectMocks
    private OrdersMessageHandler handler;
}
