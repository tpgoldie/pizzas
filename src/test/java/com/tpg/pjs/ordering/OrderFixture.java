package com.tpg.pjs.ordering;

import com.tpg.pjs.StringGeneration;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.SMALL;
import static com.tpg.pjs.pizzas.PizzaCode.THE_GREEK_CODE;
import static java.util.Collections.singletonList;

public interface OrderFixture extends OrderItemDetailsFixture, StringGeneration {

    default Order newOrder() {

        OrderItemDetails itemDetails = newOrderItem(PIZZA.getCode(), THE_GREEK_CODE.getValue(),
                12.99, 1, SMALL.name(), THIN_CRUST.name(), false);

        return new Order.Builder()
                        .sessionId(generateString(5))
                        .userId("jdoe")
                        .dateOrdered("12-08-2017 19:15")
                        .itemsOrdered(singletonList(itemDetails))
                        .build();
    }
}
