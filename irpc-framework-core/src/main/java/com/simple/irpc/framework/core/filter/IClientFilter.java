package com.simple.irpc.framework.core.filter;

import com.simple.irpc.framework.core.common.ChannelFutureWrapper;
import com.simple.irpc.framework.core.common.RpcInvocation;

import java.util.List;

/**
 * 客户端过滤器
 */
public interface IClientFilter extends IFilter{

    /**
     * 执行过滤链
     *
     * @param src
     * @param rpcInvocation
     * @return
     */
    void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation);
}
