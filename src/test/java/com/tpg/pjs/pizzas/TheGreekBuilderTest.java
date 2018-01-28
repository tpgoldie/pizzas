package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.MEDIUM;
import static com.tpg.pjs.pizzas.Pizza.Type.VEGETARIAN;
import static com.tpg.pjs.pizzas.PizzasAssertions.assertThat;

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
                .hasName("The Greek")
                .hasType(VEGETARIAN)
                .hasSize(MEDIUM)
                .hasCrustiness(THIN_CRUST)
                .hasDescription(DESCRIPTION)
                .hasIngredients(Cheese.ingredient(), BlackOlives.ingredient(), FetaCheese.ingredient(),
                        Tomatoes.ingredient(), Pepperoncini.ingredient())
                .withStuffedCrust(false);
    }
}
