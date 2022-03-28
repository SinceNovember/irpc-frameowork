package com.simple.irpc.framework.core.common;

import java.util.concurrent.atomic.AtomicLong;
import static com.simple.irpc.framework.core.common.cache.CommonClientCache.SERVICE_ROUTER_MAP;

/**
 * 实现轮训效果，它的本质就是通过取模计算
 */
public class ChannelFuturePollingRef {

    private AtomicLong referenceTimes = new AtomicLong(0);

    public ChannelFutureWrapper getChannelFutureWrapper(String serviceName){
        ChannelFutureWrapper[] arr = SERVICE_ROUTER_MAP.get(serviceName);
        long i = referenceTimes.getAndIncrement();
        int index = (int) (i % arr.length);
        return arr[index];
    }
}
