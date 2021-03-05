package com.price.processor.throttler;

import com.price.processor.PriceProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceProcessorImpl implements PriceProcessor {
    private ConcurrentHashMap<String, Double> prices = new ConcurrentHashMap<>();
    private PriceThrottler priceThrottler;

    public PriceProcessorImpl(PriceThrottler priceThrottler) {
        this.priceThrottler = priceThrottler;
    }

    @Override
    public void onPrice(String ccyPair, double rate) {
        rate = priceThrottler.getTasks().get(ccyPair);
        prices.put(ccyPair, rate);
    }

    @Override
    public void subscribe(PriceProcessor priceProcessor) {

    }

    @Override
    public void unsubscribe(PriceProcessor priceProcessor) {

    }

    public Map<String, Double> getPrices() {
        return prices;
    }

}
