package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.services.activiti.OrdersStatusMessageHandler.OrderEnvelope;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.core.JmsOperations;

import static com.tpg.pjs.ordering.Order.Status.ACCEPTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class SendOrderStatusMessage extends OrderMessaging {

    public static SendOrderStatusMessage given() {

        return new SendOrderStatusMessage();
    }

    SendOrderStatusMessage handler(OrdersStatusMessageHandler handler) {

        this.handler = handler;

        return this;
    }

    SendOrderStatusMessage jmsOperations(JmsOperations jmsOperations) {

        this.jmsOperations = jmsOperations;

        return this;
    }

    SendOrderStatusMessage when() { return this; }

    SendOrderStatusMessage sendOrderStatusMessage(Order order) {

        this.order = order;

        handler.send(order, ACCEPTED);

        return this;
    }

    SendOrderStatusMessage then() { return this; }

    SendOrderStatusMessage orderStatusIsSent() {

        ArgumentCaptor<OrderEnvelope> argumentCaptor = ArgumentCaptor.forClass(OrderEnvelope.class);

        verify(jmsOperations).convertAndSend(eq("orders-status-queue"), argumentCaptor.capture());

        OrderEnvelope actual = argumentCaptor.getValue();

        assertThat(actual.getOrder()).isEqualTo(order);

        return this;
    }

    private SendOrderStatusMessage() {}

    private OrdersStatusMessageHandler handler;
}
