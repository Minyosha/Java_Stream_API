package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class StartClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            InetAddress Address = InetAddress.getLocalHost();

            Socket socket = new Socket(Address, 5000);
            Client client = new Client(socket, name);

            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("Local port: " + socket.getLocalPort());
            System.out.println("For private messaging, please enter the user's name at the beginning of the message like that:");
            System.out.println("@" + name + "@ your message");
            System.out.println("Enter !exit! to exit the chat.");

            client.listenForMessages();
            client.sendMessage();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}