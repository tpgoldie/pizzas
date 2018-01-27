package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.Cheese;
import com.tpg.pizzas.domain.ingredients.JalapenoPeppers;
import com.tpg.pizzas.domain.ingredients.Pepperoni;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.Pizza.Size.LARGE;
import static com.tpg.pizzas.domain.Pizza.Type.MEATS;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

public class AmericanHotBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new AmericanHot.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        String description = "This all-time classic pizza is back by popular demand.";

        AmericanHot actual = (AmericanHot) builder.size(LARGE).crustiness(DEEP_CRUST)
                                    .description(description)
                                    .withStuffedCrust(true)
                                    .build();

        assertThat(actual)
            .hasType(MEATS)
            .hasSize(LARGE)
            .hasDescription(description)
            .hasCrustiness(DEEP_CRUST)
            .hasIngredients(Cheese.ingredient(), JalapenoPeppers.ingredient(), Pepperoni.ingredient())
            .withStuffedCrust(true);
    }
}
