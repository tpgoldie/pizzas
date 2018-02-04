package com.tpg.pjs.pizzas;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum PizzaCode {

    AMERICAN_HOT_CODE("AH"), THE_GREEK_CODE("TG"), PAPAS_FAVOURITE_CODE("PF"), CHICKEN_CLUB_CODE("CC"), PREMIUM_HAWAIIAN_CODE("PH");

    public static Optional<PizzaCode> byCode(String value) {

        return Arrays.stream(com.tpg.pjs.pizzas.PizzaCode.values()).filter(code -> code.getValue().equals(value)).findFirst();
    }

    @Getter
    private final String value;

    PizzaCode(String value) {

        this.value = value;
    }
}
