package com.tpg.pjs.services.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.pjs.ordering.Order;
import org.activiti.engine.RuntimeService;
import org.apache.activemq.command.ActiveMQBytesMessage;

import javax.jms.BytesMessage;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class ReadOrderFromQueue {

    public static ReadOrderFromQueue given() {

        return new ReadOrderFromQueue();
    }

    ReadOrderFromQueue ordersMessageSender(OrdersMessageSender ordersMessageSender) {

        this.ordersMessageSender = ordersMessageSender;

        return this;
    }

    ReadOrderFromQueue orderReader(OrderReader orderReader) {

        this.orderReader = orderReader;

        return this;
    }

    ReadOrderFromQueue runtimeService(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;

        return this;
    }

    ReadOrderFromQueue order(Order order) {

        this.order = order;

        return this;
    }

    ReadOrderFromQueue when() { return this; }

    ReadOrderFromQueue readOrderFromQueue() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        message = new ActiveMQBytesMessage();
        message.writeBytes(objectMapper.writeValueAsBytes(order));

        orderReader.receiveMessage(order);

        return this;
    }

    ReadOrderFromQueue then() { return this; }

    ReadOrderFromQueue orderIsReadFromQueue() {

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("newOrder", order);

        verify(runtimeService).startProcessInstanceByKey("ORDER_FULFILMENT", mapping);

        return this;
    }

    private OrderReader orderReader;
    private RuntimeService runtimeService;
    private OrdersMessageSender ordersMessageSender;
    private BytesMessage message;
    private Order order;
}
