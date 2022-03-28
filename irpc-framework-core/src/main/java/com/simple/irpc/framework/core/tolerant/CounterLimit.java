package com.simple.irpc.framework.core.tolerant;

import java.util.concurrent.TimeUnit;

public abstract class CounterLimit {

    protected int limitCount;

    protected long limitTime;

    protected TimeUnit timeUnit;

    protected volatile boolean isLimited;

    protected abstract boolean tryCount();

    public CounterLimit(int limitCount, long limitTime, TimeUnit timeUnit) {
        this.limitCount = limitCount;
        this.limitTime = limitTime;
        this.timeUnit = timeUnit;
    }
}