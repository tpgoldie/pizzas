package com.tpg.pjs.ordering;

import com.tpg.pjs.ordering.Order.Status;
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
    private Status status = PENDING;
    private List<OrderItemDetails> orderItems;
}
