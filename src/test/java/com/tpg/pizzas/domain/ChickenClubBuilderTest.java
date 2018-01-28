package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.Pizza.Size.LARGE;
import static com.tpg.pizzas.domain.Pizza.Type.MEATS;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

public class ChickenClubBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new ChickenClub.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        ChickenClub actual = (ChickenClub) builder.size(LARGE).crustiness(DEEP_CRUST)
                                    .description(DESCRIPTION)
                                    .withStuffedCrust(true)
                                    .build();

        assertThat(actual)
            .hasName("Chicken Club")
            .hasType(MEATS)
            .hasSize(LARGE)
            .hasDescription(DESCRIPTION)
            .hasCrustiness(DEEP_CRUST)
            .hasIngredients(Chicken.ingredient(), Bacon.ingredient(), Cheese.ingredient(),
                    Onions.ingredient(), Tomatoes.ingredient())
            .withStuffedCrust(true);
    }
}
