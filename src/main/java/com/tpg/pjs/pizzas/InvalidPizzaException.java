package com.tpg.pjs.pizzas;

public abstract class InvalidPizzaException extends Exception {

    protected InvalidPizzaException(String msg) {

        super(msg);
    }
}
