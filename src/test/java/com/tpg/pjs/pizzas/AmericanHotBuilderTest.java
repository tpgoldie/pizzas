package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.Cheese;
import com.tpg.pjs.pizzas.ingredients.JalapenoPeppers;
import com.tpg.pjs.pizzas.ingredients.Pepperoni;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;
import static com.tpg.pjs.pizzas.PizzasAssertions.assertThat;

public class AmericanHotBuilderTest extends PizzaBuilderTest {

    @Before
    public void setUp() {

        builder = new AmericanHot.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        AmericanHot actual = (AmericanHot) builder.size(LARGE).crustiness(DEEP_CRUST)
                                    .description(DESCRIPTION)
                                    .withStuffedCrust(true)
                                    .build();

        assertThat(actual)
            .hasName("American Hot")
            .hasItemTypeCode(PIZZA.getCode())
            .hasItemCode(AMERICAN_HOT_CODE.getValue())
            .hasType(MEATS)
            .hasSize(LARGE)
            .hasDescription(DESCRIPTION)
            .hasCrustiness(DEEP_CRUST)
            .hasIngredients(Cheese.ingredient(), JalapenoPeppers.ingredient(), Pepperoni.ingredient())
            .withStuffedCrust(true);
    }
}
