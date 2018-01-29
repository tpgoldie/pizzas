package com.tpg.pjs.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class PjsEntity {

    @Column
    private long id;
}
