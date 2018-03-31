package com.tpg.pjs.services.activiti;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderItemDetailsFixture;
import com.tpg.pjs.pizzas.Pizza;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
public abstract class OrderMessagingTest implements StringGeneration, OrderItemDetailsFixture {

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

    @Mock
    OrdersMessageSender ordersMessageSender;

    Order order;
}
