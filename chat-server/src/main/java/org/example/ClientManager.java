package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) throws IOException {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println("SERVER: " + name + " has joined the chat!");
            broadcastMessage("SERVER: " + name + " has joined the chat!");

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClient() {
        clients.remove(this);
        broadcastMessage("SERVER: " + name + " has left the chat!");
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void broadcastMessage(String message) {
        if (message == null) return;

        for (ClientManager client : clients) {
            try {
                if (client.name.equals(name)) continue;

                if (isPrivateMessage(message)) {
                    String recipientName = getRecipientName(message);
                    String msg = getMessageText(message).trim();

                    if (client.name.equals(recipientName)) {
                        sendMessageToClient(client, "Private message from " + name + ": " + msg);
                    }
                } else {
                    sendMessageToClient(client, message);
                    System.out.println(message + " sent to " + client.name);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private boolean isPrivateMessage(String message) {
        int twoDotsPosition = message.indexOf(":");
        return (message.indexOf("@") == twoDotsPosition + 2);
    }

    private String getRecipientName(String message) {
        int firstAt = message.indexOf("@");
        int secondAt = message.indexOf("@", firstAt + 1);
        return (secondAt != -1) ? message.substring(firstAt + 1, secondAt) : null;
    }

    private String getMessageText(String message) {
        int secondAt = message.indexOf("@", message.indexOf("@") + 1);
        return (secondAt != -1) ? message.substring(secondAt + 1) : null;
    }


    private void sendMessageToClient(ClientManager client, String msg) throws IOException {
        client.bufferedWriter.write(msg);
        client.bufferedWriter.newLine();
        client.bufferedWriter.flush();
    }


}



