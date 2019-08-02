package client;

import handler.BaseHandler;
import handler.client.ImClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author huliang
 * @date 2019-08-02 11:39
 */
public class TimeClient {

    private ChannelInitializer<SocketChannel> initializer;
    public BaseHandler handler = null;

    public TimeClient(ChannelInitializer<SocketChannel> initializer) {
        this.initializer = initializer;
    }

    public TimeClient(ChannelInitializer<SocketChannel> initializer, BaseHandler handler) {
        this.initializer = initializer;
        this.handler = handler;
    }

    public void run() {
        String host = "localhost";
        int port = 8000;
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(initializer);

        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (handler instanceof ImClientHandler) {
                ImClientHandler imClientHandler = (ImClientHandler)handler;
                imClientHandler.workGroup = workGroup;
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

}
