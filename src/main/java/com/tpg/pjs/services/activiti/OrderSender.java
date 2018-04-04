package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import static com.tpg.pjs.ordering.Order.Status.ACCEPTED;

@Component
public class OrderSender implements OrderAcceptance, JavaDelegate {

    @Autowired
    OrderSender(OrdersMessageSender ordersMessageSender, OrdersStatusMessageSender ordersStatusMessageSender, MessageCreator messageCreator) {

        this.ordersMessageSender = ordersMessageSender;

        this.ordersStatusMessageSender = ordersStatusMessageSender;

        this.messageCreator = messageCreator;
    }

    @Override
    public void placeOnQueue(Order order) {

        ordersMessageSender.send(order);

        LOG.info("Received order {}/{}", order.getUserId(), order.getSessionId());

        ordersStatusMessageSender.send(order, ACCEPTED);
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }

    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);

    private final OrdersMessageSender ordersMessageSender;
    private final OrdersStatusMessageSender ordersStatusMessageSender;
    private final MessageCreator messageCreator;

}
