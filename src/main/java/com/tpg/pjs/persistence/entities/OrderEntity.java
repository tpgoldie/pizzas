package com.tpg.pjs.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "seq_generator", sequenceName = "pjs.orders_seq", allocationSize = 1)
public class OrderEntity extends PjsEntity {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "order_placed")
    private ZonedDateTime orderPlaced;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;
}
