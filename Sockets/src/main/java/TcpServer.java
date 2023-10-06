import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//https://www.digitalocean.com/community/tutorials/java-socket-programming-server-client

public class TcpServer extends Thread {

    private static Socket serverSocket;


    public TcpServer(Socket serverSocket) {
        serverSocket = serverSocket;

    }

    @Override
    public void run() {
   // public static void main(String[] args) throws ClassNotFoundException, IOException {
    try {
                 //serverSocket = new ServerSocket(port);
                //Socket socket1 = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
                ObjectOutputStream objectOutput = new ObjectOutputStream(serverSocket.getOutputStream());

                while (true) {
                    System.out.println("Waiting for client request...");


                    // how increment number sended from client if inputStream message converted to string?
                    String message = (String) objectInputStream.readObject();
                    System.out.println("Message received: " + message);
                    objectOutput.writeObject("Hello, Server at your service " + message);
                    if (message.equals("exit")) {
                        break;}

                    try {
                        Thread.sleep(15000);

                        } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                        }

                    try {
                        objectInputStream.close();
                        objectOutput.close();
                        serverSocket.close();
                        } catch (IOException e) {
                        System.out.println("Oops : " + e.getMessage());
                        }

                }
            } catch (IOException | ClassNotFoundException e) {
        System.out.println("Oops : " + e.getMessage());
    }
    }
}


