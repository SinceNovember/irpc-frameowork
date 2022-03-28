package com.simple.irpc.framework.core.exception;

import com.simple.irpc.framework.core.common.RpcInvocation;

public class MaxServiceLimitRequestException extends IRpcException{

    public MaxServiceLimitRequestException(RpcInvocation rpcInvocation) {
        super(rpcInvocation);
    }
}