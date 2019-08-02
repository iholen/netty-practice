package handler.server;

import handler.BaseHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author huliang
 * @date 2019-07-30 16:07
 */
public class EchoServerHandler extends BaseHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        ctx.flush();
    }

}
