package com.simple.irpc.framework.core.common.event.listener;

import com.simple.irpc.framework.core.common.ChannelFutureWrapper;
import com.simple.irpc.framework.core.common.event.IRpcEvent;
import com.simple.irpc.framework.core.common.event.IRpcNodeChangeEvent;
import com.simple.irpc.framework.core.register.URL;
import com.simple.irpc.framework.core.register.zookeeper.ProviderNodeInfo;

import java.util.List;
import static com.simple.irpc.framework.core.common.cache.CommonClientCache.*;

/**
 * 提供节点变化监听器
 */
public class ProviderNodeDataChangeListener implements IRpcListener<IRpcNodeChangeEvent> {

    @Override
    public void callBack(Object t) {
        ProviderNodeInfo providerNodeInfo = ((ProviderNodeInfo) t);
        List<ChannelFutureWrapper> channelFutureWrappers =  CONNECT_MAP.get(providerNodeInfo.getServiceName());
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String address = channelFutureWrapper.getHost()+":"+channelFutureWrapper.getPort();
            if(address.equals(providerNodeInfo.getAddress())){
                //修改权重
                channelFutureWrapper.setWeight(providerNodeInfo.getWeight());
                URL url = new URL();
                url.setServiceName(providerNodeInfo.getServiceName());
                //更新权重
                IROUTER.updateWeight(url);
                break;
            }
        }
    }
}
