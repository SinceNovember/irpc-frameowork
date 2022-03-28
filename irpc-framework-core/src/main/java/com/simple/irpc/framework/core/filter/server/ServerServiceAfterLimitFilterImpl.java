package com.simple.irpc.framework.core.filter.server;

import com.simple.irpc.framework.core.common.RpcInvocation;
import com.simple.irpc.framework.core.common.ServerServiceSemaphoreWrapper;
import com.simple.irpc.framework.core.common.annotations.SPI;
import com.simple.irpc.framework.core.filter.IServerFilter;
import static com.simple.irpc.framework.core.common.cache.CommonServerCache.SERVER_SERVICE_SEMAPHORE_MAP;

@SPI("after")
public class ServerServiceAfterLimitFilterImpl implements IServerFilter {

    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        String serviceName = rpcInvocation.getTargetServiceName();
        ServerServiceSemaphoreWrapper serverServiceSemaphoreWrapper = SERVER_SERVICE_SEMAPHORE_MAP.get(serviceName);
        serverServiceSemaphoreWrapper.getSemaphore().release();
    }
}