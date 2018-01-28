package com.tpg.pjs.ordering;

public interface OrderItemDetailsFixture {
    default OrderItemDetails newOrderItem(String code, double price, int quantity,
                                          String size, String crustiness, boolean withStuffedCrust) {

        OrderItemDetails item = new OrderItemDetails();

        item.setCode(code);
        item.setPrice(price);
        item.setSize(size);
        item.setQuantity(quantity);
        item.setCrustiness(crustiness);
        item.setStuffedCrust(withStuffedCrust);

        return item;
    }
}
