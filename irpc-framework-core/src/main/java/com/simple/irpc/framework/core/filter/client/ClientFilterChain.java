package com.simple.irpc.framework.core.filter.client;

import com.simple.irpc.framework.core.common.ChannelFutureWrapper;
import com.simple.irpc.framework.core.common.RpcInvocation;
import com.simple.irpc.framework.core.filter.IClientFilter;

import java.util.ArrayList;
import java.util.List;

public class ClientFilterChain {

    private static List<IClientFilter> iClientFilterList = new ArrayList<>();

    public void addClientFilter(IClientFilter clientFilter) {
        iClientFilterList.add(clientFilter);
    }

    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        for (IClientFilter iClientFilter : iClientFilterList) {
            iClientFilter.doFilter(src, rpcInvocation);
        }
    }
}
