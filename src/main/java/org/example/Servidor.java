package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketException;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException{super("servidor");} //Se usa el constructor para servidor de Conexion

    public void startServer() throws IOException//Método para iniciar el servidor
    {

        System.out.println("Esperando..."); //Esperando conexión

        cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

        System.out.println("Cliente en línea");

        //Se obtiene el flujo de salida del cliente para enviarle mensajes

        //Se le envía un mensaje al cliente usando su flujo de salida
        //Se obtiene el flujo entrante desde el cliente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
        out.println("Petición recibida y aceptada");
        String mensajeCliente = "";
        String datos="";

        while ((mensajeCliente = entrada.readLine()) != null) {
            if (mensajeCliente.equals("FIN")) {
                break;
            }
            datos += mensajeCliente;
        }

            System.out.println("El mensaje del cliente: " + datos);

            int num1, num2, num3, num4, res1, res2;

            String [] parts = datos.split(" ");
            num1 = Integer.valueOf(parts[0]);
            num2 = Integer.valueOf(parts[1]);

            num3 = Integer.valueOf(parts[2]);
            num4 = Integer.valueOf(parts[3]);

            res1 = Integer.valueOf((conectarServOp1(num1, num2)));
            res2 = Integer.valueOf((realizarOperacion2(num3, num4)));

            out.println(res1+res2);

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente
        }


        public String conectarServOp1(int num1, int num2) throws IOException {
            try{
            Class<?> serverImpl1Class = Class.forName("com.example.ServerImpl1");
            Object serverImpl1 = serverImpl1Class.getDeclaredConstructor().newInstance();

            Method serverImpl1Method = serverImpl1Class.getMethod("run");
            serverImpl1Method.invoke(serverImpl1);
            }catch(Exception e){

                  e.printStackTrace();
            }
            int timeoutMillis = 5000;

            String response = null;
            try {
                cs1 = new Socket("localhost", 1235);
                BufferedReader in1 = new BufferedReader(new InputStreamReader(cs1.getInputStream()));
                PrintWriter out1 = new PrintWriter(cs1.getOutputStream(), true);
                out1.println(num1);
                out1.println(num2);
                cs1.setSoTimeout(timeoutMillis);
                response = in1.readLine();
                System.out.println("Server response: " + response);
            } catch (SocketException e2) {
                return conectarServOp2(num1, num2);
            } catch (IOException e){
                return realizarOperacion2(num1, num2);
                // Aquí puedes manejar el caso de timeout, por ejemplo, intentar nuevamente o mostrar un mensaje de error.
            }
            return (response);
        }


    public String conectarServOp2(int num3, int num4) throws IOException {
        int timeoutMillis = 5000;
        String response = null;
        try {
            
            cs2 = new Socket("localhost", 1236);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(cs2.getInputStream()));
            PrintWriter out2 = new PrintWriter(cs2.getOutputStream(), true);
            out2.println(num3);
            out2.println(num4);
            cs2.setSoTimeout(timeoutMillis);
            response = in2.readLine();
            System.out.println("Server response: " + response);
        } catch (SocketException e2) {
            return realizarOperacion1(num3, num4);
        } catch (IOException e){
            // Aquí puedes manejar el caso de timeout, por ejemplo, intentar nuevamente o mostrar un mensaje de error.
            return realizarOperacion1(num3, num4);
        }
        return (response);
    }

    public String realizarOperacion1(int num1, int num2) throws IOException {
        BufferedReader in1 = new BufferedReader(new InputStreamReader(cs1.getInputStream()));
        PrintWriter out1 = new PrintWriter(cs1.getOutputStream(), true);
        out1.println(num1);
        out1.println(num2);
        String response = in1.readLine();
        return response;
    }

    public String realizarOperacion2(int num3, int num4) throws IOException {
        try{
            BufferedReader in1 = new BufferedReader(new InputStreamReader(cs2.getInputStream()));
            PrintWriter out1 = new PrintWriter(cs2.getOutputStream(), true);
            out1.println(num3);
            out1.println(num4);
            String response = in1.readLine();
            return response;
        }
        catch (RuntimeException e){
            return conectarServOp2(num3, num4);
        } catch(IOException e2){
            return conectarServOp2(num3, num4);
        }
    }
}