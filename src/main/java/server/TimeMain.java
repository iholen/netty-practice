package server;

import handler.server.TimeServerHandler;

/**
 * @author huliang
 * @date 2019-07-30 17:17
 */
public class TimeMain {

    public static void main(String[] args) throws Exception {
        new NettyServer(8000, new TimeServerHandler()).run();
    }

}
