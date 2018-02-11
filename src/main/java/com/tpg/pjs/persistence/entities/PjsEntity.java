package com.tpg.pjs.persistence.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class PjsEntity implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator")
    private Long id;
}
