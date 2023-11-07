package org.example;

import java.io.IOException;

//Clase principal que hará uso del servidor
public class MainServidor
{
    public static void main(String[] args) throws IOException
    {
        Server serv = new Server(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
    }
}