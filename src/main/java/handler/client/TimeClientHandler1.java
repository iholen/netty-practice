package handler.client;

import handler.BaseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.Date;

/**
 * 拆、粘包 解决方法1:
 *   将所有接收到的数据放在统一的缓冲中，达到指定字节大小时再处理.
 *
 * @author huliang
 * @date 2019-07-30 16:07
 */
public class TimeClientHandler1 extends BaseHandler {
    private final int BYTE_COUNT = 4;
    private ByteBuf byteBuf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        byteBuf = ctx.alloc().buffer(BYTE_COUNT);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        byteBuf.release();
        byteBuf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        // 所有接收的数据都累积在 byteBuf 变量里。
        byteBuf.writeBytes(m);
        m.release();

        if (byteBuf.readableBytes() >= BYTE_COUNT) {
            long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

}
