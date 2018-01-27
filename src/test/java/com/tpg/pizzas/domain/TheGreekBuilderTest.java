package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pizzas.domain.Pizza.Size.MEDIUM;
import static com.tpg.pizzas.domain.Pizza.Type.VEGETARIAN;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

public class TheGreekBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new TheGreek.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        TheGreek actual = (TheGreek) builder.size(MEDIUM)
                .crustiness(THIN_CRUST)
                .description(DESCRIPTION)
                .build();

        assertThat(actual)
                .hasType(VEGETARIAN)
                .hasSize(MEDIUM)
                .hasCrustiness(THIN_CRUST)
                .hasDescription(DESCRIPTION)
                .hasIngredients(Cheese.ingredient(), BlackOlives.ingredient(), FetaCheese.ingredient(),
                        Tomatoes.ingredient(), Pepperoncini.ingredient())
                .withStuffedCrust(false);
    }
}
