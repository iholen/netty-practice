package client;

import handler.client.ImClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class ImClientServer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your name: ");
        String userName = sc.nextLine();

        final ImClientHandler handler = new ImClientHandler(userName);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1,
            1,
            1,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            (r) -> new Thread(r,"连接服务器线程"));
        executor.execute(() ->
            new TimeClient(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(handler);
                }
            }, handler).run());

        System.out.println("正在连接...");
        while (handler.serverCtx == null) {
            System.out.print("");
        }
        System.out.println("已连接至服务器...");

        try {
            while (true) {
                String message = sc.nextLine();
                if ("exit".equals(message)) {
                    // 离开聊天室提醒
                    handler.sendMessage(handler.userName + " has left the chat room.");
                    break;
                } else {
                    handler.sendMessage(handler.userName + " said: " + message);
                }
            }
        } finally {
            handler.workGroup.shutdownGracefully();
            executor.shutdown();
        }
    }

}
