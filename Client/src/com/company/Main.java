package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set ip and port separated by space: ");
        String[] address = scanner.nextLine().split(" ");
        try {
            Socket socket = new Socket(address[0], Integer.parseInt(address[1]));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input = "";
            while (!input.equalsIgnoreCase("x")) {
                System.out.print("Message: ");
                input = scanner.nextLine();
                out.println(input);
                System.out.println(in.readLine());
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Address not found.");
        }
    }
}