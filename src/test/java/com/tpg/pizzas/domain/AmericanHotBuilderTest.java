package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.toppings.Cheese;
import com.tpg.pizzas.domain.toppings.JalapenoPeppers;
import com.tpg.pizzas.domain.toppings.Pepperoni;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Size.LARGE;
import static com.tpg.pizzas.domain.Pizza.Type.MEATS;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

public class AmericanHotBuilderTest {

    private AmericanHot.Builder builder;

    @Before
    public void setUp() {
        builder = new AmericanHot.Builder();
    }

    @Test
    public void build() {

        String description = "This all-time classic pizza is back by popular demand.";

        AmericanHot actual = (AmericanHot) builder.size(LARGE).type(MEATS)
                                    .description(description)
                                    .withStuffedCrust(true)
                                    .build();

        assertThat(actual)
            .hasType(MEATS)
            .hasSize(LARGE)
            .hasDescription(description)
            .hasToppings(Cheese.topping(), JalapenoPeppers.topping(), Pepperoni.topping())
            .withStuffedCrust(true);
    }
}
