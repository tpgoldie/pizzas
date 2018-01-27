package com.tpg.pizzas.domain;

public class Pizzas {

    public static Pizza.Builder americanHot() { return new AmericanHot.Builder(); }

    public static Pizza.Builder theGreek() { return new TheGreek.Builder(); }

    public static Pizza.Builder premiumHawaiian() { return new PremiumHawaiian.Builder(); }
}
