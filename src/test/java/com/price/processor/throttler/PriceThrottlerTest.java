package com.price.processor.throttler;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceThrottlerTest {
    @Test
    public void checkThrottler() {
        PriceThrottler priceThrottler = new PriceThrottler();
        PriceProcessorImpl priceProcessor = new PriceProcessorImpl(priceThrottler);
        priceThrottler.subscribe(priceProcessor);

        priceThrottler.onPrice("USDRUB", 75.0);

        double actual = priceProcessor.getPrices().get("USDRUB");

        assertEquals(75.0, actual);
    }
}
