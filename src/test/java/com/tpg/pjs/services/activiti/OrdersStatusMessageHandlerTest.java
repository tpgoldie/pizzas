package com.tpg.pjs.services.activiti;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jms.core.JmsOperations;

import static com.tpg.pjs.services.activiti.SendOrderStatusMessage.given;

public class OrdersStatusMessageHandlerTest extends OrderMessagingTest {

    @Test
    public void sendMessage() {

        given()
            .handler(handler)
            .jmsOperations(jmsOperations)
        .when()
            .sendOrderStatusMessage(order)
        .then()
            .orderStatusIsSent();
    }

    @Mock
    private JmsOperations jmsOperations;

    @InjectMocks
    private OrdersStatusMessageHandler handler;
}
