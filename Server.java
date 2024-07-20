package server;

import java.net.*;
import java.io.*;

public class Server {
    //initialize socket and input stream
    private final int port;
    private ServerSocket server;
    private final boolean status;

    public Server(int port) {
        this.port = port;
        this.status = startServer();
    }

    public boolean startServer() {
        try {
            this.server = new ServerSocket(this.port);
        } catch (IOException ioException) {
            System.err.println(ioException.toString());
            return false;
        }
        System.out.println("Server acceso");
        return true;
    }

    // visualizzazione del traffico in ingresso sulla console
    public void printInputData() {
        if (!this.status) {
            System.err.println("Server non raggiungibile");
            return;
        }

        Socket socket = null;
        BufferedReader messageFromClient = null;

        try {
            socket = this.server.accept();
            System.out.println("Client accettato");

            // Reading data from client
            messageFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            System.out.println("Stringa ricevuta: ");

            while ((inputLine = messageFromClient.readLine()) != null) {
                System.out.println(inputLine);
                System.out.flush();  // Ensure immediate display of the message
                if (inputLine.equals("Over")) {
                    break;
                }
            }

        } catch (IOException ioException) {
            System.err.println("Errore durante la comunicazione: " + ioException.toString());
        } finally {
            // Ensure resources are closed
            try {
                if (messageFromClient != null) {
                    messageFromClient.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioException) {
                System.err.println("Errore durante la chiusura delle risorse: " + ioException.toString());
            }
        }
    }
}
