package com.tpg.pjs.persistence.entities;

import java.math.BigDecimal;

public interface OrderItemEntityFixture {

    default OrderItemEntity newOrderItemEntity(String itemTypeCode, String itemCode, String size,
                                               String crustiness, BigDecimal price, int quantity) {

        OrderItemEntity entity = new OrderItemEntity();

        entity.setItemTypeCode(itemTypeCode);
        entity.setItemCode(itemCode);
        entity.setSize(size);
        entity.setCrustiness(crustiness);
        entity.setPrice(price);
        entity.setQuantity(quantity);

        return entity;
    }
}
