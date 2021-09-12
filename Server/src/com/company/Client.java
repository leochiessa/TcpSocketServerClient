package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private final Socket clientStocket;

    public Client(Socket clientStocket) {
        this.clientStocket = clientStocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientStocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientStocket.getInputStream()));
            System.out.println("Client " + clientStocket.getInetAddress() + " connected.");
            String line = "";
            do {
                if (!line.equals("")) {
                    System.out.println(clientStocket.getInetAddress() + " says: " + line);
                    out.println("\n" + line);
                }
            } while ((!(line = in.readLine()).equalsIgnoreCase("x")));
            out.println("\nBye.");
            in.close();
            out.close();
            clientStocket.close();
            System.out.println("Client " + clientStocket.getInetAddress() + " disconnected.");
        } catch (Exception e) {
        }
    }
}