package com.tpg.pjs.services.activiti;

import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.tpg.pjs.services.activiti.ReadOrderFromQueue.given;

public class OrderReaderTest extends OrderMessagingTest {

    @Test
    public void readOrderFromQueue() throws Exception {
        given()
            .runtimeService(runtimeService)
            .ordersMessageSender(ordersMessageSender)
            .orderReader(orderReader)
            .order(order)
        .when()
            .readOrderFromQueue()
        .then()
            .orderIsReadFromQueue();
    }

    @Mock
    private RuntimeService runtimeService;

    @InjectMocks
    private OrderReader orderReader;
}
