## [Netty](https://netty.io)
1. Discard Server
    1. [DiscardServerHandler](./src/main/java/handler/server/DiscardServerHandler.java)
    2. [DiscardServer](./src/main/java/server/DiscardServer.java)
2. Echo Server
    1. [EchoServerHandler](./src/main/java/handler/server/EchoServerHandler.java)
    2. [EchoServer](./src/main/java/server/EchoServer.java)
3. Time Server
    1. [TimeServerHandler](./src/main/java/handler/server/TimeServerHandler.java)
    2. [TimeServer](./src/main/java/server/TimeServer.java)
4. Time Client
    1. [TimeClientHandler](./src/main/java/handler/client/TimeClientHandler.java)
    2. [TimeClientServer](./src/main/java/client/TimeClientServer.java)
5. Dealing with a Stream-based Transport
    1. First Solution
        1. Modify handler `TimeClientHandler` to [TimeClientHandler1](./src/main/java/handler/client/TimeClientHandler1.java)
        2. [TimeClientServer1](./src/main/java/client/TimeClientServer1.java)
    2. Second Solution
        1. Add [TimeDecoder](./src/main/java/decoder/TimeDecoder.java)
        2. [TimeClientServer2](./src/main/java/handler/client/TimeClientHandler2.java)
6. Speaking in POJO instead of ByteBuf
7. Shutting Down Your Application
8. IM Chat Room
    1. Server
        1. [ImServerHandler](./src/main/java/handler/server/ImServerHandler.java)
        2. [ImServer](./src/main/java/server/ImServer.java)
    2. Client
        1. [ImClientHandler](./src/main/java/handler/client/ImClientHandler.java)
        2. [ImClientServer](./src/main/java/client/ImClientServer.java)
