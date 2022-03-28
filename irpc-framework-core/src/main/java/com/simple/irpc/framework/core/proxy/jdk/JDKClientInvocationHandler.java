package com.simple.irpc.framework.core.proxy.jdk;

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
/**
 * JDK代理的客户端反射InvocationHandler
 */
public class JDKClientInvocationHandler implements InvocationHandler {

    private final static Object OBJECT = new Object();

    private RpcReferenceWrapper rpcReferenceWrapper;

    private int timeOut = DEFAULT_TIMEOUT;

    public JDKClientInvocationHandler(RpcReferenceWrapper rpcReferenceWrapper) {
        this.rpcReferenceWrapper = rpcReferenceWrapper;
        timeOut = Integer.valueOf(String.valueOf(rpcReferenceWrapper.getAttatchments().get("timeOut")));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcInvocation rpcInvocation = new RpcInvocation();
        rpcInvocation.setArgs(args);
        rpcInvocation.setTargetMethod(method.getName());
        rpcInvocation.setTargetServiceName(rpcReferenceWrapper.getAimClass().getName());
        rpcInvocation.setUuid(UUID.randomUUID().toString());
        rpcInvocation.setAttachments(rpcReferenceWrapper.getAttatchments());
        //往发送队列中发送信息，队列会一直循环去获取结果
        SEND_QUEUE.add(rpcInvocation);
        if (rpcReferenceWrapper.isAsync()) {
            return null;
        }
        long beginTime = System.currentTimeMillis();
        RESP_MAP.put(rpcInvocation.getUuid(), OBJECT);
        //如果这里有多个io线程负责监听消息信息，是否效率会更高呢？
        //一直等待服务端处理完后，发送结果给客户端，客户端会往RESP_MAP中设置值，然后就可以获取到
        while (System.currentTimeMillis() - beginTime < timeOut) {
            Object object = RESP_MAP.get(rpcInvocation.getUuid());
            if (object instanceof RpcInvocation) {
                return ((RpcInvocation) object).getResponse();
            }
        }
        throw new TimeoutException("wait for response from server on client " + timeOut + "ms!");
    }
}
