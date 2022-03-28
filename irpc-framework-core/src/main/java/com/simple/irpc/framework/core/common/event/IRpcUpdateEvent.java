package com.simple.irpc.framework.core.common.event;

/**
 * 节点更新事件
 */
public class IRpcUpdateEvent implements IRpcEvent{

    private Object data;

    public IRpcUpdateEvent(Object data) {
        this.data = data;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public IRpcEvent setData(Object data) {
        this.data = data;
        return this;
    }
}
