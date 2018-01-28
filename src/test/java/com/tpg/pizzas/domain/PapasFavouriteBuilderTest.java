package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.Pizza.Size.SMALL;
import static com.tpg.pizzas.domain.Pizza.Type.MEATS;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

public class PapasFavouriteBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new PapasFavourite.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        PapasFavourite actual = (PapasFavourite) builder.size(SMALL).crustiness(DEEP_CRUST)
                                    .description(DESCRIPTION)
                                    .withStuffedCrust(true)
                                    .build();

        assertThat(actual)
            .hasName("The Papa's Favourite")
            .hasType(MEATS)
            .hasSize(SMALL)
            .hasDescription(DESCRIPTION)
            .hasCrustiness(DEEP_CRUST)
            .hasIngredients(ItalianSixCheeseBlend.ingredient(), PorkSausage.ingredient(), Cheese.ingredient(),
                    Pepperoni.ingredient(), ItalianSeasoning.ingredient(), ItalianSixCheeseBlend.ingredient())
            .withStuffedCrust(true);
    }
}
