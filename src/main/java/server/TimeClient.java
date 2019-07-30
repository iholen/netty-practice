package server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import server.handler.TimeClientHandler;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8000;
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

        ChannelFuture future = bootstrap.connect(host, port).sync();
        future.channel().closeFuture().sync();
        workGroup.shutdownGracefully();
    }

}
