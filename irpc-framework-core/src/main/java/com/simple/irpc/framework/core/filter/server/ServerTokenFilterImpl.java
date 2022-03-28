package com.simple.irpc.framework.core.filter.server;

import com.simple.irpc.framework.core.common.RpcInvocation;
import com.simple.irpc.framework.core.common.utils.CommonUtils;
import com.simple.irpc.framework.core.filter.IServerFilter;
import com.simple.irpc.framework.core.server.ServiceWrapper;
import static com.simple.irpc.framework.core.common.cache.CommonServerCache.*;
/**
 * 简单版本的token校验
 *
 */
public class ServerTokenFilterImpl implements IServerFilter {

    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        String token = String.valueOf(rpcInvocation.getAttachments().get("serviceToken"));
        ServiceWrapper serviceWrapper = PROVIDER_SERVICE_WRAPPER_MAP.get(rpcInvocation.getTargetServiceName());
        String matchToken = String.valueOf(serviceWrapper.getServiceToken());
        if (CommonUtils.isEmpty(matchToken)) {
            return;
        }
        if (!CommonUtils.isEmpty(token) && token.equals(matchToken)) {
            return;
        }
        throw new RuntimeException("token is " + token + " , verify result is false!");
    }
}