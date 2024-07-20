package server;

public class Main{

    public static void main(String[] args) {

        Server myServer = new Server(500);

        while (true){
            myServer.printInputData();
        }

    }
}