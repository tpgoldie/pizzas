package com.tpg.pizzas.domain;

public abstract class InvalidPizzaException extends Exception {

    protected InvalidPizzaException(String msg) {

        super(msg);
    }
}
