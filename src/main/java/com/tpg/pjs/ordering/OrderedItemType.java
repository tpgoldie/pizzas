package com.tpg.pjs.ordering;


import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum OrderedItemType {

    PIZZA("PZ");

    public static Optional<OrderedItemType> byCode(String value) {

        return Arrays.stream(values()).filter(v -> v.getCode().equals(value)).findFirst();
    }

    private final String code;

    OrderedItemType(String code) {

        this.code = code;
    }
}
