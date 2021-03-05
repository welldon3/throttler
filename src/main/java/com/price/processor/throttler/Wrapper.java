package com.price.processor.throttler;

import java.util.concurrent.RecursiveAction;

public class Wrapper extends RecursiveAction {
    private final String ccyPair;
    private final double rate;

    public Wrapper(String ccyPair, double rate) {
        this.ccyPair = ccyPair;
        this.rate = rate;
    }

    @Override
    protected void compute() {
        System.out.println("It works!");
    }
}
