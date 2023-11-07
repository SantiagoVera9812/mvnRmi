package org.example;

import java.io.IOException;
import java.util.Random;

import org.zeromq.ZMQ;

public class Client {

    Client(){};

    public void startClient() throws IOException//Método para iniciar el servidor
    {
        try (ZMQ.Context context = ZMQ.context(1);
             ZMQ.Socket socket = context.socket(ZMQ.PUSH)) {
            socket.connect("tcp://localhost:5555"); // Connect to the broker

            Random random = new Random();
            int num1 = random.nextInt(1000); // Generate 4 random numbers
            int num2 = random.nextInt(1000);
            int num3 = random.nextInt(1000);
            int num4 = random.nextInt(1000);

            System.out.println(num1);
            System.out.println(num2);
            System.out.println(num3);

            String request = num1 + " " + num2 + " " + num3 + " " + num4;
            String message = request;

            socket.send(message);
        }
    }
}