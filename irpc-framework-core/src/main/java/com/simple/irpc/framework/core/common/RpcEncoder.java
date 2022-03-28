package com.simple.irpc.framework.core.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import static com.simple.irpc.framework.core.common.constants.RpcConstants.DEFAULT_DECODE_CHAR;

/**
 * RPC编码器
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol, ByteBuf out) throws Exception {
        out.writeShort(rpcProtocol.getMagicNumber());
        out.writeInt(rpcProtocol.getContentLength());
        out.writeBytes(rpcProtocol.getContent());
        //添加一个分隔符来区分包
        out.writeBytes(DEFAULT_DECODE_CHAR.getBytes());

    }
}
