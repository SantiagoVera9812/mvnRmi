package org.example;

import org.zeromq.ZMQ;

public class Broker {
    public static void main(String[] args) {
        try (ZMQ.Context context = ZMQ.context(1);
             ZMQ.Socket frontend = context.socket(ZMQ.SUB);
             ZMQ.Socket backend = context.socket(ZMQ.PUB)) {
            frontend.bind("tcp://*:5555");  // Clients connect here
            backend.bind("tcp://*:5556");   // Servers connect here

            frontend.subscribe("".getBytes()); // Subscribe to all messages

            while (true) {
                // Receive a message from a client
                byte[] message = frontend.recv();
                
                backend.send(message);
            }
        }
    }
}
