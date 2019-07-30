package server;

import handler.server.EchoServerHandler;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class EchoMain {

    public static void main(String[] args) throws Exception {
        new NettyServer(8000, new EchoServerHandler()).run();
    }

}
