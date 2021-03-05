package com.price.processor.throttler;

import com.price.processor.PriceProcessor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

public class PriceThrottler implements PriceProcessor {
    private final Set<PriceProcessor> priceProcessors = new ConcurrentSkipListSet<>();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private final Map<String, Double> tasks = new ConcurrentHashMap<>();

    public PriceThrottler() {
    }

    public void onPrice(String ccyPair, double rate) {
        priceProcessors.forEach(priceProcessors -> execute(ccyPair, rate));
        tasks.put(ccyPair, rate);
    }

    public void subscribe(PriceProcessor priceProcessor) {
        priceProcessors.add(priceProcessor);
    }

    public void unsubscribe(PriceProcessor priceProcessor) {
        priceProcessors.remove(priceProcessor);
    }

    public void execute(String ccyPair, double rate) {
        Wrapper wrapper = new Wrapper(ccyPair, rate);
        forkJoinPool.execute(wrapper);
    }

    public Map<String, Double> getTasks() {
        return tasks;
    }

}
