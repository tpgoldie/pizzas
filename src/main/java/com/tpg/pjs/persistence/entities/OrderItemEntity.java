package com.tpg.pjs.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItemEntity extends PjsEntity {

    @ManyToMany
    private OrderEntity order;

    @Column
    private BigDecimal price;

    @Column
    private int quantity;
}
