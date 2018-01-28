package com.tpg.pjs.pizzas;

import lombok.Getter;

@Getter
public enum Codes {

    AMERICAN_HOT_CODE("AH"), THE_GREEK_CODE("TG"), PAPAS_FAVOURITE_CODE("PF"), CHICKEN_CLUB_CODE("CC"), PREMIUM_HAWAIIAN_CODE("PH");

    private final String value;

    Codes(String value) {

        this.value = value;
    }
}
