package org.example;

import java.io.IOException;

public class MainServidorOp2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServidorOp2 servop2 = new ServidorOp2(); //Se crea el servidor

        System.out.println("Iniciando el servidor de operación 2\n");
        servop2.startServer(); //Se inicia el servidor
    }
}