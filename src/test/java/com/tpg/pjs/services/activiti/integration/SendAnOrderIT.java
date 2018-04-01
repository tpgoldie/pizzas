package com.tpg.pjs.services.activiti.integration;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.services.activiti.OrderReader;
import com.tpg.pjs.services.activiti.OrderSender;
import org.activiti.engine.RuntimeService;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendAnOrderIT implements StringGeneration, OrderDetailsRequestFixture {

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
    public void deliverOrder() throws InterruptedException {

        orderSender.placeOnQueue(order);

        latch.await(5000, MILLISECONDS);

        Map<String, Object> mapping = new HashMap<>();

        mapping.put("newOrder", order);

        verify(runtimeService).startProcessInstanceByKey("ORDER_FULFILMENT", mapping);
    }

    @ClassRule
    public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private OrderReader orderReader;

    @MockBean
    private RuntimeService runtimeService;

    private Order order;

    private CountDownLatch latch = new CountDownLatch(1);
}
