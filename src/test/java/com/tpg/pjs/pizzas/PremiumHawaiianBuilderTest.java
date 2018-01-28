package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.pizzas.Codes.PREMIUM_HAWAIIAN_CODE;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pjs.pizzas.Pizza.Size.SMALL;
import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;
import static com.tpg.pjs.pizzas.PizzasAssertions.assertThat;

public class PremiumHawaiianBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new PremiumHawaiian.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        PremiumHawaiian actual = (PremiumHawaiian) builder.size(SMALL)
                .description(DESCRIPTION)
                .build();

        assertThat(actual)
                .hasName("Premium Hawaiian")
                .hasCode(PREMIUM_HAWAIIAN_CODE.getValue())
                .hasType(MEATS)
                .hasSize(SMALL)
                .hasCrustiness(ORIGINAL)
                .hasDescription(DESCRIPTION)
                .hasIngredients(Cheese.ingredient(), Pineapple.ingredient(), PulledHamHock.ingredient())
                .withStuffedCrust(false);
    }
}
