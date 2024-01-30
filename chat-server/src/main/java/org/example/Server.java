package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer() throws IOException {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e) {
            closeSocket();
        }

    }

    public void closeSocket() throws IOException {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
