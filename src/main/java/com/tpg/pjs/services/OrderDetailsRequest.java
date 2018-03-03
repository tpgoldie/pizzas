package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order.Status;
import com.tpg.pjs.ordering.OrderItemDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.tpg.pjs.ordering.Order.Status.PENDING;

@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailsRequest {

    private String userId;
    private String sessionId;
    private Status status;
    private String dateOrdered;
    private List<OrderItemDetails> orderedItems;
}
