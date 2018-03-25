package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@EqualsAndHashCode
@Builder
public final class OrderDetailsStatus {

    private final String userId;
    private final String sessionId;
    private final String orderId;
    private final Order.Status orderStatus;

    public boolean matches(OrderDetailsRequest request) {

        return new EqualsBuilder()
                .append(request.getUserId(), userId)
                .append(request.getSessionId(), sessionId)
                .append(request.getStatus(), orderStatus)
                .isEquals();
    }
}
