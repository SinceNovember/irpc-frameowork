package com.simple.irpc.framework.core.common.cache;

import com.simple.irpc.framework.core.common.ServerServiceSemaphoreWrapper;
import com.simple.irpc.framework.core.common.config.ServerConfig;
import com.simple.irpc.framework.core.dispatcher.ServerChannelDispatcher;
import com.simple.irpc.framework.core.filter.server.ServerAfterFilterChain;
import com.simple.irpc.framework.core.filter.server.ServerBeforeFilterChain;
import com.simple.irpc.framework.core.filter.server.ServerFilterChain;
import com.simple.irpc.framework.core.register.RegistryService;
import com.simple.irpc.framework.core.register.URL;
import com.simple.irpc.framework.core.register.zookeeper.AbstractRegister;
import com.simple.irpc.framework.core.serialize.SerializeFactory;
import com.simple.irpc.framework.core.server.ServiceWrapper;
import io.netty.util.internal.ConcurrentSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务缓存
 */
public class CommonServerCache {

    public static final Map<String,Object> PROVIDER_CLASS_MAP = new ConcurrentHashMap<>();
    public static final Set<URL> PROVIDER_URL_SET = new ConcurrentSet<>();
    public static AbstractRegister REGISTRY_SERVICE;
    public static SerializeFactory SERVER_SERIALIZE_FACTORY;
    public static ServerConfig SERVER_CONFIG;
    public static ServerBeforeFilterChain SERVER_BEFORE_FILTER_CHAIN;
    public static ServerAfterFilterChain SERVER_AFTER_FILTER_CHAIN;
    public static final Map<String, ServiceWrapper> PROVIDER_SERVICE_WRAPPER_MAP = new ConcurrentHashMap<>();
    public static Boolean IS_STARTED = false;
    public static ServerChannelDispatcher SERVER_CHANNEL_DISPATCHER = new ServerChannelDispatcher();
    public static final Map<String, ServerServiceSemaphoreWrapper> SERVER_SERVICE_SEMAPHORE_MAP = new ConcurrentHashMap<>(64);

}
