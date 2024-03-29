package server;

import handler.server.DiscardServerHandler;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class DiscardServer {

    public static void main(String[] args) throws Exception {
        int port = 8000;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new NettyServer(port, new DiscardServerHandler()).run();
    }

}
