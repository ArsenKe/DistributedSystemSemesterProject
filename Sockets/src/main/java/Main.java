import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main (String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            while(true) {
                new TcpServer(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
