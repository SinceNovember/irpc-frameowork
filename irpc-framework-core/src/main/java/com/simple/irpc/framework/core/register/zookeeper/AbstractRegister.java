package com.simple.irpc.framework.core.register.zookeeper;

import com.simple.irpc.framework.core.register.RegistryService;
import com.simple.irpc.framework.core.register.URL;

import java.util.List;
import java.util.Map;

import static com.simple.irpc.framework.core.common.cache.CommonClientCache.*;
import static com.simple.irpc.framework.core.common.cache.CommonServerCache.*;

public abstract class AbstractRegister implements RegistryService {

    @Override
    public void register(URL url) {
        PROVIDER_URL_SET.add(url);
    }

    @Override
    public void unRegister(URL url) {
        PROVIDER_URL_SET.remove(url);
    }

    @Override
    public void subscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.add(url);
    }

    @Override
    public void doUnSubscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.remove(url.getServiceName());
    }

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doAfterSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doBeforeSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param serviceName
     * @return
     */

    public abstract List<String> getProviderIps(String serviceName);
    /**
     * 获取服务的权重信息
     *
     * @param serviceName
     * @return <ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>
     */
    public abstract Map<String, String> getServiceWeightMap(String serviceName);

}
