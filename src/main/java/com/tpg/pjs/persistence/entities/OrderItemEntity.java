package com.tpg.pjs.persistence.entities;


import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class OrderItemEntity extends PjsEntity {

    @ManyToMany
    private OrderEntity order;

    @Column(name = "item_type_code")
    private String itemTypeCode;

    @Column(name = "item_code")
    private String itemCode;

    @Column
    private String size;

    @Column
    private String crustiness;

    @Column
    private BigDecimal price;

    @Column
    private int quantity;
}
