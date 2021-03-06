package com.simple.irpc.framework.core.common;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RPC反射信息
 */
public class RpcInvocation {

    //请求的目标方法，例如findUser
    private String targetMethod;

    //请求的目标服务名称，例如：com.sise.user.UserService
    private String targetServiceName;

    //请求参数信息
    private Object[] args;

    private String uuid;

    //接口响应的数据塞入这个字段中（如果是异步调用或者void类型，这里就为空）
    private Object response;

    private Throwable e;

    private int retry;

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    private Map<String, Object> attachments = new ConcurrentHashMap<>();

    public Map<String, Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, Object> attachments) {
        this.attachments = attachments;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public String getTargetServiceName() {
        return targetServiceName;
    }

    public void setTargetServiceName(String targetServiceName) {
        this.targetServiceName = targetServiceName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    @Override
    public String toString() {
        return "RpcInvocation{" +
                "targetMethod='" + targetMethod + '\'' +
                ", targetServiceName='" + targetServiceName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", response=" + response +
                ", e=" + e +
                ", retry=" + retry +
                ", attachments=" + attachments +
                '}';
    }
}
