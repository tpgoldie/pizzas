package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OrderItemDetails {

    private String itemTypeCode;
    private String itemCode;
    private double price;
    private int quantity;
    private String size;
    private String crustiness;
    private boolean stuffedCrust;

    @Override
    public String toString() {
        String msg = stuffedCrust ? "with stuffed crust" : "";

        return String.format("Ordered %d of %s %s %s %s @ %.2f %s", quantity, size, itemTypeCode, itemCode, crustiness,
                price, msg);
    }
}
