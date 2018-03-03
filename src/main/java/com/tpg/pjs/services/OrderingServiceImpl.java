package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;

import java.util.Optional;

public class OrderingServiceImpl implements OrderingService {

    private final OrdersLifecycleRepository ordersLifecycleRepository;

    private final OrderPlacement orderPlacement;

    private final RequestToEntityConverter requestToEntityConverter;
    private final RequestToDomainConverter requestToDomainConverter;

    public OrderingServiceImpl(OrdersLifecycleRepository ordersLifecycleRepository, OrderPlacement orderPlacement) {

        this(ordersLifecycleRepository, orderPlacement, new OrderRequestToOrderEntityConverter(), new OrderRequestToOrderDomainConverter());
    }

    OrderingServiceImpl(OrdersLifecycleRepository ordersLifecycleRepository, OrderPlacement orderPlacement,
                        RequestToEntityConverter requestToEntityConverter,
                        RequestToDomainConverter requestToDomainConverter) {

        this.ordersLifecycleRepository = ordersLifecycleRepository;

        this.orderPlacement = orderPlacement;

        this.requestToEntityConverter = requestToEntityConverter;

        this.requestToDomainConverter = requestToDomainConverter;
    }

    @Override
    public Optional<OrderDetailsResponse> placeOrder(OrderDetailsRequest request) {

        OrderEntity orderEntity = requestToEntityConverter.convert(request);

        ordersLifecycleRepository.save(orderEntity);

        Order order = requestToDomainConverter.convert(request);

        OrderDetailsStatus orderDetailsStatus = orderPlacement.placeOrder(order);

        return orderDetailsStatus.matches(request)
                ? Optional.of(OrderDetailsResponse.builder()
                    .orderDetailsRequest(request)
                    .orderId(orderDetailsStatus.getOrderId())
                    .build())
                : Optional.empty();
    }
}
