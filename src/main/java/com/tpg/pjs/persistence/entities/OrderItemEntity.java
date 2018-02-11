package com.tpg.pjs.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
@SequenceGenerator(name = "seq_generator", sequenceName = "pjs.order_items_seq", allocationSize = 1)
public class OrderItemEntity extends PjsEntity {

    @ManyToOne
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
