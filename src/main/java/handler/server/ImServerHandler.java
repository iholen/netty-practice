package handler.server;

import handler.BaseHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huliang
 * @date 2019-07-30 16:07
 */
@ChannelHandler.Sharable
public class ImServerHandler extends BaseHandler {

    private List<ChannelHandlerContext> ctxList = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctxList.add(ctx);
        System.out.println("当前连接数：" + ctxList.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctxList.remove(ctx);
        System.out.println("当前连接数：" + ctxList.size());
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        ctxList.forEach(tCtx -> {
            if (!tCtx.equals(ctx)) {
                tCtx.writeAndFlush(ReferenceCountUtil.retain(msg));
            }
        });
        ctx.flush();
    }

}
