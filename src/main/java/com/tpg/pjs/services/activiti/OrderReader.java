package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import java.util.HashMap;
import java.util.Map;

import static com.tpg.pjs.config.ActiveMQConfig.ORDER_QUEUE;

public class OrderReader implements OrderDeliverance {

    @Override
    @JmsListener(destination = ORDER_QUEUE)
    public void receiveMessage(Order order) {

        LOG.info("Received order {}/{}", order.getUserId(), order.getSessionId());

        runtimeService.startProcessInstanceByKey(ORDER_FULFILMENT, generateMapping(order));
    }

    private Map<String, Object> generateMapping(Order order) {

        Map<String, Object> mapping = new HashMap<>();

        mapping.put(OBJECT_KEY, order);

        return mapping;
    }

    @Autowired
    public OrderReader(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;
    }

    private static final String ORDER_FULFILMENT = "ORDER_FULFILMENT";
    private static final String OBJECT_KEY = "newOrder";

    private static final Logger LOG = LoggerFactory.getLogger(OrderReader.class);

    private final RuntimeService runtimeService;
}
