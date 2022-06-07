package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set ip and port separated by space: ");
        String[] address = scanner.nextLine().split(" ");
        try {
            Socket socket = new Socket(address[0], Integer.parseInt(address[1]));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input = "";
            while (!input.equalsIgnoreCase("x")) {
                System.out.println("Message: ");
                input = scanner.nextLine();
                out.println(input);
                String ans = in.readUTF();
                System.out.println(ans);
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Address not found.");
        }
    }
}