package com.simple.irpc.framework.core.tolerant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LimitRequestV1 {

    private int limit;

    private int times;

    private AtomicInteger request = new AtomicInteger(0);

    private TimeUnit timeUnit;

    public LimitRequestV1(int limit, int times, TimeUnit timeUnit) {
        this.limit = limit;
        this.times = times;
        this.timeUnit = timeUnit;
    }

    public void doRequest() {
        synchronized (this) {
            if (request.get() >= limit) {
                throw new RuntimeException("请求超过限制流量");
            }
            request.incrementAndGet();
        }
    }

    public void afterRequest() {
        synchronized (this) {
            request.decrementAndGet();
        }
    }
}
