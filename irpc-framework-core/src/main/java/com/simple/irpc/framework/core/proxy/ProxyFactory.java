package com.simple.irpc.framework.core.proxy;

import com.simple.irpc.framework.core.client.RpcReferenceWrapper;

public interface ProxyFactory {

    <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable;
}
