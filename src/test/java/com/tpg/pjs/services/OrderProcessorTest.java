package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderItemDetailsFixture;
import com.tpg.pjs.pizzas.Pizza;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static com.tpg.pjs.services.ProcessingAnOrder.given;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
public class OrderProcessorTest implements StringGeneration, OrderItemDetailsFixture {

    @Before
    public void setUp() {
        order = new Order.Builder()
                .dateOrdered("23/12/2016 12:15:30")
                .sessionId(generateString(5))
                .userId("pjs")
                .itemsOrdered(singletonList(newOrderItem(
                        PIZZA.getCode(), AMERICAN_HOT_CODE.getValue(),15.95,
                        3, Pizza.Size.MEDIUM.name(), DEEP_CRUST.name(),
                        true)))
                .build();

    }

    @Test
    public void processOrder() {

        given()
            .orderProcessor(orderProcessor)
            .runtimeService(runtimeService)
            .processInstance(processInstance)
        .when()
            .processingAnOrder(order)
        .then()
            .startANewProcessInstance();
    }

    private Order order;

    @Mock
    private RuntimeService runtimeService;

    @Mock
    private ProcessInstance processInstance;

    @InjectMocks
    private OrderProcessor orderProcessor;
}
