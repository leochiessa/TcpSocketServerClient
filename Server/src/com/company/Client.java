package com.company;

import java.io.*;
import java.net.Socket;
import java.io.DataOutputStream;

public class Client extends Thread {
    private final Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream dis = new DataInputStream(System.in);
            System.out.println("Client " + clientSocket.getInetAddress() + " connected.");
            String line = "";
            while ((!(line = in.readLine()).equalsIgnoreCase("x"))) {
                if (!line.equals("")) {
                    System.out.println(clientSocket.getInetAddress() + " says: " + line);
                    System.out.println("Response: ");
                    String rsp = dis.readLine();
                    out.writeUTF("Server says: " + rsp);
                }
            }
            out.writeUTF("Bye.");
            in.close();
            out.close();
            dis.close();
            clientSocket.close();
            System.out.println("Client " + clientSocket.getInetAddress() + " disconnected.");
        } catch (Exception e) {
        }
    }
}