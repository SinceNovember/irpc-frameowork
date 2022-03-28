package com.simple.irpc.framework.core.proxy.javassist;

import com.simple.irpc.framework.core.client.RpcReferenceWrapper;
import com.simple.irpc.framework.core.common.RpcInvocation;
import com.simple.irpc.framework.core.common.cache.CommonClientCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import static com.simple.irpc.framework.core.common.cache.CommonClientCache.RESP_MAP;
import static com.simple.irpc.framework.core.common.cache.CommonClientCache.SEND_QUEUE;
import static com.simple.irpc.framework.core.common.constants.RpcConstants.DEFAULT_TIMEOUT;

public class JavassistInvocationHandler implements InvocationHandler {

    private final static Object OBJECT = new Object();

    private RpcReferenceWrapper rpcReferenceWrapper;

    private Long timeOut = Long.valueOf(DEFAULT_TIMEOUT);

    public JavassistInvocationHandler(RpcReferenceWrapper rpcReferenceWrapper) {
        this.rpcReferenceWrapper = rpcReferenceWrapper;
        timeOut = Long.valueOf(String.valueOf(rpcReferenceWrapper.getAttatchments().get("timeOut")));
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcInvocation rpcInvocation = new RpcInvocation();
        rpcInvocation.setArgs(args);
        rpcInvocation.setTargetMethod(method.getName());
        rpcInvocation.setTargetServiceName(rpcReferenceWrapper.getAimClass().getName());
        rpcInvocation.setUuid(UUID.randomUUID().toString());
        SEND_QUEUE.add(rpcInvocation);
        //既然是一步请求，就没有必要再在RESP_MAP中判断是否有响应结果了
        if (rpcReferenceWrapper.isAsync()) {
            return null;
        }

        RESP_MAP.put(rpcInvocation.getUuid(), OBJECT);
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime < timeOut) {
            Object object = RESP_MAP.get(rpcInvocation.getUuid());
            if (object instanceof RpcInvocation) {
                return ((RpcInvocation) object).getResponse();
            }
        }
        //修改异常信息
        throw new TimeoutException("Wait for response from server on client " + timeOut + "ms,Service's name is " + rpcInvocation.getTargetServiceName() + "#" + rpcInvocation.getTargetMethod());
    }
}
