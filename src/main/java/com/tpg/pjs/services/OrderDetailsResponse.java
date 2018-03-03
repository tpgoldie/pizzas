package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
public final class OrderDetailsResponse {

    private final String userId;
    private final String orderId;
    private final OrderDetailsRequest orderDetailsRequest;
    private final Order.Status orderStatus;
}
