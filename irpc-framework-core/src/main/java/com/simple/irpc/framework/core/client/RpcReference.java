package com.simple.irpc.framework.core.client;


import com.simple.irpc.framework.core.proxy.ProxyFactory;
import static com.simple.irpc.framework.core.common.cache.CommonClientCache.CLIENT_CONFIG;

/**
 * RPC接口代理
 */
public class RpcReference {

    public ProxyFactory proxyFactory;

    public RpcReference(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    public <T> T get(RpcReferenceWrapper<T> rpcReferenceWrapper) throws Throwable {
        initGlobalRpcReferenceWrapperConfig(rpcReferenceWrapper);
        return proxyFactory.getProxy(rpcReferenceWrapper);
    }

    /**
     * 初始化远程调用的一些全局配置,例如超时
     *
     * @param rpcReferenceWrapper
     */
    private void initGlobalRpcReferenceWrapperConfig(RpcReferenceWrapper rpcReferenceWrapper) {
        if (rpcReferenceWrapper.getTimeOUt() == null) {
            rpcReferenceWrapper.setTimeOut(CLIENT_CONFIG.getTimeOut());
        }
    }
}
