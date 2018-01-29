package com.tpg.pjs.ordering;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Order {

    public enum Status { PENDING, COMPLETED, CANCELLED }

    private final List<OrderItem> items = new ArrayList<>();

    private String userId;

    private LocalDateTime orderPlaced;

    public Order(OrderDetailsRequest request) {
    }
}
