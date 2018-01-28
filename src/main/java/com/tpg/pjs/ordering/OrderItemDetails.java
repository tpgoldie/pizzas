package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OrderItemDetails {

    private String code;
    private double price;
    private int quantity;
    private String size;
    private String crustiness;
    private boolean stuffedCrust;
}
