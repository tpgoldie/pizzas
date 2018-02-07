package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.AmericanHot;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static com.tpg.pjs.ordering.OrderedItemBuilding.when;
import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderedItemBuildingTest implements OrderDetailsRequestFixture {

    @Test
    public void buildOrderedPizza_orderedPizza_shouldBuildAnOrderedPizza() throws InvalidPizzaException {

        OrderItemDetails item = newOrderItem(PIZZA.getCode(), AMERICAN_HOT_CODE.getValue(), 15.45, 1, LARGE.name(),
                THIN_CRUST.name(), false);

        Pizza.Builder pizzaBuilder = new AmericanHot.Builder();

        Optional<OrderItem> actual = when(item.getItemTypeCode()).matchesPizza().buildOrderedPizza(pizzaBuilder, item);

        assertThat(actual).isNotEmpty();

        Pizza expectedPizza = new AmericanHot.Builder()
                .name("American Hot")
                .itemCode(AMERICAN_HOT_CODE.getValue())
                .crustiness(THIN_CRUST)
                .size(LARGE)
                .build();

        OrderingAssertions.assertThat((OrderedPizza) actual.get())
                .hasCost(new Cost(new BigDecimal(15.45)))
                .hasQuantity(new Quantity(1))
                .hasPizza(expectedPizza);
    }
}
