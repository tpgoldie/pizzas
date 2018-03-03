package com.tpg.pjs.ordering;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza.Crustiness;
import com.tpg.pjs.pizzas.Pizza.Size;
import com.tpg.pjs.pizzas.PizzaCode;
import com.tpg.pjs.services.OrderDetailsRequest;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static java.util.Collections.singletonList;

public interface OrderDetailsRequestFixture extends OrderItemDetailsFixture, StringGeneration {

    default OrderDetailsRequest orderAPizza(String userId, String dateOrdered,
                                            PizzaCode pizzaCode, Size size, Crustiness crustiness,
                                            double price, int quantity) throws InvalidPizzaException {

        OrderDetailsRequest request = new OrderDetailsRequest();

        request.setSessionId(generateString(10));

        request.setUserId(userId);

        request.setDateOrdered(dateOrdered);

        request.setOrderedItems(singletonList(newOrderItem(PIZZA.getCode(), pizzaCode.getValue(),
                price, quantity, size.name(), crustiness.name(), true)));

        return request;
    }
}
