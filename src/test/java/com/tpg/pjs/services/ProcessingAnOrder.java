package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

class ProcessingAnOrder implements StringGeneration {

    static ProcessingAnOrder given() {

        return new ProcessingAnOrder();
    }

    private ProcessingAnOrder() {}

    ProcessingAnOrder orderProcessor(OrderProcessor orderProcessor) {

        this.orderProcessor = orderProcessor;

        return this;
    }

    ProcessingAnOrder runtimeService(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;

        return this;
    }

    ProcessingAnOrder processInstance(ProcessInstance processInstance) {

        this.processInstance = processInstance;

        return this;
    }

    ProcessingAnOrder when() { return this; }

    ProcessingAnOrder processingAnOrder(Order order) {

        newOrder = order;

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("newOrder", order);

        Mockito.when(runtimeService.startProcessInstanceByKey("PlaceOrder", mapping)).thenReturn(processInstance);

        Mockito.when(processInstance.getName()).thenReturn(generateString(5));

        orderProcessor.startProcessing(order);

        return this;
    }

    ProcessingAnOrder then() { return this; }

    ProcessingAnOrder startANewProcessInstance() {

        ArgumentCaptor<Map> argumentCaptor = ArgumentCaptor.forClass(Map.class);

        verify(runtimeService).startProcessInstanceByKey(eq("PlaceOrder"), argumentCaptor.capture());

        Map<String, Object> actual = argumentCaptor.getValue();

        Order order = (Order) actual.get("newOrder");

        assertEquals("Order not set in process instance", newOrder, order);
        return this;
    }

    private OrderProcessor orderProcessor;
    private Order newOrder;

    private RuntimeService runtimeService;
    private ProcessInstance processInstance;
}
