package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.*;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.MEDIUM;
import static com.tpg.pjs.pizzas.PizzaCode.*;

public class OrderedPizzaBuilderSelectorTest {

    private OrderedPizzaBuilderSelector selector;

    @Before
    public void setUp() {

        selector = new OrderedPizzaBuilderSelector();
    }

    @Test
    public void buildAmericanHotBuilder() throws InvalidPizzaException {

        AmericanHot actual = (AmericanHot) selector.select(AMERICAN_HOT_CODE.getValue()).get()
                    .size(MEDIUM)
                    .crustiness(THIN_CRUST)
                    .withStuffedCrust(false)
                    .build();

        PizzasAssertions.assertThat(actual)
                .hasName("American Hot")
                .hasItemTypeCode(PIZZA.getCode())
                .hasItemCode(AMERICAN_HOT_CODE.getValue())
                .hasSize(MEDIUM)
                .hasCrustiness(THIN_CRUST)
                .withStuffedCrust(false);
    }

    @Test
    public void buildPapasFavouriteBuilder() throws InvalidPizzaException {

        PapasFavourite actual = (PapasFavourite) selector.select(PAPAS_FAVOURITE_CODE.getValue()).get()
                    .size(MEDIUM)
                    .crustiness(THIN_CRUST)
                    .withStuffedCrust(true)
                    .build();

        PizzasAssertions.assertThat(actual)
                .hasName("The Papa's Favourite")
                .hasItemTypeCode(PIZZA.getCode())
                .hasItemCode(PAPAS_FAVOURITE_CODE.getValue())
                .hasSize(MEDIUM)
                .hasCrustiness(THIN_CRUST)
                .withStuffedCrust(true);
    }

    @Test
    public void buildTheGreekBuilder() throws InvalidPizzaException {

        TheGreek actual = (TheGreek) selector.select(THE_GREEK_CODE.getValue()).get()
                    .size(MEDIUM)
                    .crustiness(THIN_CRUST)
                    .withStuffedCrust(true)
                    .build();

        PizzasAssertions.assertThat(actual)
                .hasName("The Greek")
                .hasItemTypeCode(PIZZA.getCode())
                .hasItemCode(THE_GREEK_CODE.getValue())
                .hasSize(MEDIUM)
                .hasCrustiness(THIN_CRUST)
                .withStuffedCrust(true);
    }

    @Test
    public void buildChickenClubBuilder() throws InvalidPizzaException {

        ChickenClub actual = (ChickenClub) selector.select(CHICKEN_CLUB_CODE.getValue()).get()
                    .size(MEDIUM)
                    .crustiness(DEEP_CRUST)
                    .withStuffedCrust(true)
                    .build();

        PizzasAssertions.assertThat(actual)
                .hasName("Chicken Club")
                .hasItemTypeCode(PIZZA.getCode())
                .hasItemCode(CHICKEN_CLUB_CODE.getValue())
                .hasSize(MEDIUM)
                .hasCrustiness(DEEP_CRUST)
                .withStuffedCrust(true);
    }

    @Test
    public void buildPremiumHawaiianBuilder() throws InvalidPizzaException {

        PremiumHawaiian actual = (PremiumHawaiian) selector.select(PREMIUM_HAWAIIAN_CODE.getValue()).get()
                    .size(MEDIUM)
                    .crustiness(DEEP_CRUST)
                    .withStuffedCrust(true)
                    .build();

        PizzasAssertions.assertThat(actual)
                .hasName("Premium Hawaiian")
                .hasItemTypeCode(PIZZA.getCode())
                .hasItemCode(PREMIUM_HAWAIIAN_CODE.getValue())
                .hasSize(MEDIUM)
                .hasCrustiness(DEEP_CRUST)
                .withStuffedCrust(true);
    }
}
