package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private final Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Client " + clientSocket.getInetAddress() + " connected.");
            String line = "";
            do {
                if (!line.equals("")) {
                    System.out.println(clientSocket.getInetAddress() + " says: " + line);
                    out.println("\n" + line);
                }
            } while ((!(line = in.readLine()).equalsIgnoreCase("x")));
            out.println("\nBye.");
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Client " + clientSocket.getInetAddress() + " disconnected.");
        } catch (Exception e) {
        }
    }
}