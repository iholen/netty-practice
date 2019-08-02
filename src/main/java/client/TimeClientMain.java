package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import handler.client.TimeClientHandler;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class TimeClientMain {

    public static void main(String[] args) {
        new TimeClient(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeClientHandler());
            }
        }).run();
    }

}
