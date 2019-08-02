package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author huliang
 * @date 2019-08-02 11:25
 */
public class TimeDecoder extends ByteToMessageDecoder {
    // ByteToMessageDecoder 是 ChannelInboundHandler 的实现类，在处理数据拆分的问题上变得很简单

    private final int BYTE_COUNT = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        // 有新数据接收时，都会调用decode()方法来处理内部的那个累计缓冲

        if (in.readableBytes() < BYTE_COUNT) {
            return;
        }

        out.add(in.readBytes(BYTE_COUNT));
    }
}
