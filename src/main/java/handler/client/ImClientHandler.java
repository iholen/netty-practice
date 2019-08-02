package handler.client;

import handler.BaseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author huliang
 * @date 2019-07-30 16:07
 */
public class ImClientHandler extends BaseHandler {

    public ChannelHandlerContext serverCtx = null;

    public void sendMessage(String message) {
        serverCtx.writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.serverCtx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        StringBuffer buffer = new StringBuffer();
        try {
            while (in.isReadable()) {
                buffer.append((char)in.readByte());
            }
            if (buffer.length() > 0) {
                System.out.println(buffer.toString());
                System.out.flush();
            }
        } finally {
            /* you can also use `in.release();` */
            ReferenceCountUtil.release(msg);
        }
    }

}
