package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServidorOp2 extends Conexion{
    public ServidorOp2() throws IOException {super("servidorop2");}
    public void startServer() throws IOException, InterruptedException//Método para iniciar el servidor
    {
        String snum1, snum2;
        System.out.println("Esperando..."); //Esperando conexión

        cs2 = ss2.accept(); //Accept comienza el socket y espera una conexión desde un cliente

        System.out.println("Cliente en línea");

        //Se obtiene el flujo de salida del cliente para enviarle mensajes

        //Se le envía un mensaje al cliente usando su flujo de salida
        //Se obtiene el flujo entrante desde el cliente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cs2.getInputStream()));
        PrintWriter out = new PrintWriter(cs2.getOutputStream(), true);

        snum1 = entrada.readLine();
        snum2 = entrada.readLine();

        int num1 = Integer.valueOf(snum1);
        int num2 = Integer.valueOf(snum2);

        int resultado = num1 + num2;
        System.out.println("Resultado suma 2: " + resultado);

        out.println(resultado);
        Thread.sleep(1000);
        if(entrada.ready()){
            String snum3, snum4;
            snum3 = entrada.readLine();
            snum4 = entrada.readLine();
            int num3 = Integer.valueOf(snum3);
            int num4 = Integer.valueOf(snum4);

            int resultado2 = num3 + num4;

            System.out.println("Resultado suma 1: " + resultado2);

            out.println(resultado2);
        }
        ss2.close();
    }
}
