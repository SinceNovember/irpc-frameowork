package com.simple.irpc.framework.core.filter;

import com.simple.irpc.framework.core.common.RpcInvocation;

/**
 * 服务端过滤器
 *
 */
public interface IServerFilter extends IFilter {

    /**
     * 执行核心过滤逻辑
     *
     * @param rpcInvocation
     */
    void doFilter(RpcInvocation rpcInvocation);
}