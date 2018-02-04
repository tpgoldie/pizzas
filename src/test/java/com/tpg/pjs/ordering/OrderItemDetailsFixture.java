package com.tpg.pjs.ordering;

public interface OrderItemDetailsFixture {
    default OrderItemDetails newOrderItem(String itemTypeCode, String itemCode, double price, int quantity,
                                          String size, String crustiness, boolean withStuffedCrust) {

        OrderItemDetails item = new OrderItemDetails();

        item.setItemTypeCode(itemTypeCode);
        item.setItemCode(itemCode);
        item.setPrice(price);
        item.setSize(size);
        item.setQuantity(quantity);
        item.setCrustiness(crustiness);
        item.setStuffedCrust(withStuffedCrust);

        return item;
    }
}
