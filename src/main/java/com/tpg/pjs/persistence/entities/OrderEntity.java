package com.tpg.pjs.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends PjsEntity {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_placed")
    private LocalDateTime orderPlaced;

    @ManyToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;
}
