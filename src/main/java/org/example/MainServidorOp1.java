package org.example;

import java.io.IOException;

public class MainServidorOp1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServidorOp1 servop1 = new ServidorOp1(); //Se crea el servidor

        System.out.println("Iniciando el servidor de operación 1\n");
        servop1.startServer(); //Se inicia el servidor
    }
}
