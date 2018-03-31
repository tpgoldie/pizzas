package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.services.activiti.OrdersMessageHandler.OrderEnvelope;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.core.JmsOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class SendOrderMessage extends OrderMessaging {

    public static SendOrderMessage given() {

        return new SendOrderMessage();
    }

    SendOrderMessage handler(OrdersMessageHandler handler) {

        this.handler = handler;

        return this;
    }

    SendOrderMessage jmsOperations(JmsOperations jmsOperations) {

        this.jmsOperations = jmsOperations;

        return this;
    }

    SendOrderMessage when() { return this; }

    SendOrderMessage sendOrderMessage(Order order) {

        this.order = order;

        handler.send(order);

        return this;
    }

    SendOrderMessage then() { return this; }

    SendOrderMessage orderIsSent() {

        ArgumentCaptor<OrderEnvelope> argumentCaptor =
                forClass(OrderEnvelope.class);

        verify(jmsOperations).convertAndSend(eq("orders-queue"), argumentCaptor.capture());

        OrderEnvelope actual = argumentCaptor.getValue();

        assertThat(actual.getOrder()).isEqualTo(order);

        return this;
    }

    private SendOrderMessage() {}

    private OrdersMessageHandler handler;
}
