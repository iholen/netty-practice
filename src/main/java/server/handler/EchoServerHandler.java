package server.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author huliang
 * @date 2019-07-30 16:07
 */
public class EchoServerHandler extends ServerHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        ctx.flush();
    }

}
