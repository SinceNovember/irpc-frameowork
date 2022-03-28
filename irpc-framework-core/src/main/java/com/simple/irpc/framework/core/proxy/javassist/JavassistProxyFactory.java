package com.simple.irpc.framework.core.proxy.javassist;

import com.simple.irpc.framework.core.client.RpcReferenceWrapper;
import com.simple.irpc.framework.core.proxy.ProxyFactory;

/**
 * Javassist代理工厂
 */
public class JavassistProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable {
        return (T)ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                rpcReferenceWrapper.getAimClass(), new JavassistInvocationHandler(rpcReferenceWrapper));
    }

}
