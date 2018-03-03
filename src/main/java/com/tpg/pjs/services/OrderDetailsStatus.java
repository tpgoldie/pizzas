package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
public final class OrderDetailsStatus {

    private final String userId;
    private final String sessionId;
    private final String orderId;
    private final Order.Status orderStatus;

    public boolean matches(OrderDetailsRequest request) {
        return false;
    }
}
