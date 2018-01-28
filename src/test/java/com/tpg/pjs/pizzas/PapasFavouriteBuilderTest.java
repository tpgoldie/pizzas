package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.pizzas.Codes.PAPAS_FAVOURITE_CODE;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.SMALL;
import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;
import static com.tpg.pjs.pizzas.PizzasAssertions.assertThat;

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
            .hasCode(PAPAS_FAVOURITE_CODE.getValue())
            .hasType(MEATS)
            .hasSize(SMALL)
            .hasDescription(DESCRIPTION)
            .hasCrustiness(DEEP_CRUST)
            .hasIngredients(ItalianSixCheeseBlend.ingredient(), PorkSausage.ingredient(), Cheese.ingredient(),
                    Pepperoni.ingredient(), ItalianSeasoning.ingredient(), ItalianSixCheeseBlend.ingredient())
            .withStuffedCrust(true);
    }
}
