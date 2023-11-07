package org.example;

import java.io.IOException;

import org.zeromq.ZMQ;

public class Server{

  
    Server(){}
    public void startServer() throws IOException//Método para iniciar el servidor
    {
       
        try (ZMQ.Context context = ZMQ.context(1);
             ZMQ.Socket frontend = context.socket(ZMQ.SUB);
             ZMQ.Socket backend = context.socket(ZMQ.PUB)) {
            frontend.connect("tcp://localhost:5556"); // Connect to the broker

            // Subscribe to all messages
            frontend.subscribe("".getBytes());

            while (true) {
                // Receive a message from the broker
                byte[] message = frontend.recv();

                // Extract the request as a string
                String request = new String(message, ZMQ.CHARSET);

                // Process the request (save the four numbers)
                int[] processedValues = processRequest(request);

                int num1 = processedValues[0];
                int num2 = processedValues[1];
                int num3 = processedValues[2];
                int num4 = processedValues[3];

                System.out.println(request);
                System.out.println(num1);
                System.out.println(num2);
                System.out.println(num3);
                System.out.println(num4);

                // No response to send back

            /* int res1 = Integer.valueOf(conectarServOp1(num1, num2));
                int res2 = Integer.valueOf(realizarOperacion2(num3, num4));

                // Prepare the response
                String response = "PartialSum: " + (res1 + res2);

                // Send the response back to the broker
                backend.send(response.getBytes(ZMQ.CHARSET)); */
            }
        }
    }
    
  
    private int[] processRequest(String request) {
        int[] processedValues = new int[4];
        String[] numbers = request.split(" ");
        if (numbers.length == 4) {
            try {
                processedValues[0] = Integer.parseInt(numbers[0]);
                processedValues[1] = Integer.parseInt(numbers[1]);
                processedValues[2] = Integer.parseInt(numbers[2]);
                processedValues[3] = Integer.parseInt(numbers[3]);
            } catch (NumberFormatException e) {
                // Handle parsing errors
                System.err.println("Failed to parse numbers.");
            }
        } else {
            // Handle incorrect request format
            System.err.println("Incorrect request format.");
        }
        return processedValues;
    }
    

    /* 

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
    } */
    

}