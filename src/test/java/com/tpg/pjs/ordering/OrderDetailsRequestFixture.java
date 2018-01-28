package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;

import static com.tpg.pjs.pizzas.Codes.PAPAS_FAVOURITE_CODE;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static java.util.Collections.singletonList;

public interface OrderDetailsRequestFixture extends OrderItemDetailsFixture {

    default OrderDetailsRequest newOrderDetailsRequest(String userId) throws InvalidPizzaException {

        OrderDetailsRequest request = new OrderDetailsRequest();

        request.setUserId(userId);

        request.setOrderItems(singletonList(newOrderItem(PAPAS_FAVOURITE_CODE.getValue(),
                16.99, 1, LARGE.name(), DEEP_CRUST.name(), true)));

        return request;
    }
}
