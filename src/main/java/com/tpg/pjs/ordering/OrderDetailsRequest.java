package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailsRequest {

    private String userId;
    private List<OrderItemDetails> orderItems;
}
