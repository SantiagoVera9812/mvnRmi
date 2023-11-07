package org.example;

import java.io.IOException;

//Clase principal que hará uso del cliente
public class MainCliente
{   
    
    public static void main(String[] args) throws IOException
    {
        Client cli = new Client(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    }
}