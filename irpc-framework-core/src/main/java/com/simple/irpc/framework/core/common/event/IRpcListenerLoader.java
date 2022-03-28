package com.simple.irpc.framework.core.common.event;

import com.simple.irpc.framework.core.common.event.listener.IRpcListener;
import com.simple.irpc.framework.core.common.event.listener.ServiceUpdateListener;
import com.simple.irpc.framework.core.common.utils.CommonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IRpcListenerLoader {

    private static List<IRpcListener> iRpcListenerList = new ArrayList<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void registerListener(IRpcListener rpcListener) {
        iRpcListenerList.add(rpcListener);
    }

    public void init() {
        registerListener(new ServiceUpdateListener());
    }

    /**
     * 获取接口上的类型
     * @param o
     * @return
     */
    public static Class<?> getInterfaceT(Object o) {
        Type[] types = o.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }
        return null;
    }

    /**
     * 同步事件处理，可能会堵塞
     *
     * @param iRpcEvent
     */
    public static void sendSyncEvent(IRpcEvent iRpcEvent) {
        System.out.println(iRpcListenerList);
        if (CommonUtils.isEmptyList(iRpcListenerList)) {
            return;
        }
        for (IRpcListener<?> iRpcListener : iRpcListenerList) {
            Class<?> type = getInterfaceT(iRpcListener);
            if (type.equals(iRpcEvent.getClass())) {
                try {
                    iRpcListener.callBack(iRpcEvent.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送事件信息，监听器调用
     * @param iRpcEvent
     */
    public static void sendEvent(IRpcEvent iRpcEvent) {
        if (CommonUtils.isEmptyList(iRpcListenerList)) {
            return;
        }
        for (IRpcListener<?> iRpcListener : iRpcListenerList) {
            Class<?> type = getInterfaceT(iRpcEvent);
            if (type.equals(iRpcEvent.getClass())) {
                executorService.execute(() -> {
                    try {
                        iRpcListener.callBack(iRpcEvent.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
