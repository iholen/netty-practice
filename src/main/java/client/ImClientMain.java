package client;

import handler.client.ImClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import java.util.Scanner;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class ImClientMain {

    public static void main(String[] args) throws Exception {
        final ImClientHandler handler = new ImClientHandler();

        Thread connectThread = new Thread(() -> {
            new TimeClient(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(handler);
                }
            }).run();
        });
        connectThread.start();

        System.out.println("正在连接...");
        while (handler.serverCtx == null) {
            System.out.print("");
        }
        System.out.println("已连接至服务器...");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            if ("exit".equals(message)) {
                break;
            } else {
                handler.sendMessage(message);
            }
        }
    }

}
