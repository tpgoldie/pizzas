package com.tpg.pjs.services;

import java.util.Optional;

public interface OrderingService {

    Optional<OrderDetailsResponse> placeOrder(OrderDetailsRequest request);
}
