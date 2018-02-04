package com.tpg.pjs.ordering;


import lombok.Getter;

@Getter
public enum OrderedItemType {

    PIZZA("PZ");

    private final String code;

    OrderedItemType(String code) {

        this.code = code;
    }
}
