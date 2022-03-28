package com.simple.irpc.framework.core.router;


import com.simple.irpc.framework.core.common.ChannelFutureWrapper;
import com.simple.irpc.framework.core.register.URL;

import java.util.List;

import static com.simple.irpc.framework.core.common.cache.CommonClientCache.*;

/**
 * 轮询策略
 */
public class RotateRouterImpl implements IRouter {

    @Override
    public void refreshRouterArr(Selector selector) {
        List<ChannelFutureWrapper> channelFutureWrappers = CONNECT_MAP.get(selector.getProviderServiceName());
        ChannelFutureWrapper[] arr = new ChannelFutureWrapper[channelFutureWrappers.size()];
        for (int i = 0; i < channelFutureWrappers.size(); i++) {
            arr[i] = channelFutureWrappers.get(i);
        }
        SERVICE_ROUTER_MAP.put(selector.getProviderServiceName(), arr);
    }

    @Override
    public ChannelFutureWrapper select(Selector selector) {
        return CHANNEL_FUTURE_POLLING_REF.getChannelFutureWrapper(selector.getProviderServiceName());
    }

    @Override
    public void updateWeight(URL url) {

    }
}
