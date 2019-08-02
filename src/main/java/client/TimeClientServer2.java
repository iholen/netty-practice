package client;

import decoder.TimeDecoder;
import handler.client.TimeClientHandler2;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class TimeClientServer2 {

    public static void main(String[] args) {
        new TimeClient(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler2());
            }
        }).run();
    }

}
