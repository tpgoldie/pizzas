package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessor implements OrderProcessing {

    @Override
    public void startProcessing(Order order) {

        Map<String, Object>  mapping = new HashMap<>();
        mapping.put("newOrder", order);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_NAME, mapping);

        LOG.info(String.format("Started new process %s", processInstance.getName()));
    }

    private static final String PROCESS_NAME = "PlaceOrder";

    private static final Logger LOG = LoggerFactory.getLogger(OrderProcessor.class);

    @Autowired
    public OrderProcessor(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;
    }

    private final RuntimeService runtimeService;
}
