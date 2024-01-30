package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            Server server = new Server(serverSocket);
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}