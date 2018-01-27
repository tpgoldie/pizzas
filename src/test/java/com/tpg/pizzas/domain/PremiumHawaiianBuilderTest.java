package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pizzas.domain.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pizzas.domain.Pizza.Size.MEDIUM;
import static com.tpg.pizzas.domain.Pizza.Size.SMALL;
import static com.tpg.pizzas.domain.Pizza.Type.MEATS;
import static com.tpg.pizzas.domain.Pizza.Type.VEGETARIAN;
import static com.tpg.pizzas.domain.PizzasAssertions.assertThat;

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
                .hasType(MEATS)
                .hasSize(SMALL)
                .hasCrustiness(ORIGINAL)
                .hasDescription(DESCRIPTION)
                .hasIngredients(Cheese.ingredient(), Pineapple.ingredient(), PulledHamHock.ingredient())
                .withStuffedCrust(false);
    }
}
