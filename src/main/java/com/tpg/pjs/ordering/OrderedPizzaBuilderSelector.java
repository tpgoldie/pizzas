package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.pizzas.PizzaCode;
import com.tpg.pjs.pizzas.Pizzas;

import java.util.Optional;

public final class OrderedPizzaBuilderSelector {

    public Optional<Pizza.Builder> select(String itemCode) {

        Optional<PizzaCode> code = PizzaCode.byCode(itemCode);

        return code.flatMap(this::lookUpBuilder);
    }

    private Optional<Pizza.Builder> lookUpBuilder(PizzaCode code) {
        switch(code) {
            case AMERICAN_HOT_CODE:
                return Optional.of(Pizzas.americanHot());

            case THE_GREEK_CODE:
                return Optional.of(Pizzas.theGreek());

            case PAPAS_FAVOURITE_CODE:
                return Optional.of(Pizzas.papasFavourite());

            case CHICKEN_CLUB_CODE:
                return Optional.of(Pizzas.chickenClub());

            case PREMIUM_HAWAIIAN_CODE:
                return Optional.of(Pizzas.premiumHawaiian());
        }

        return Optional.empty();
    }
}
