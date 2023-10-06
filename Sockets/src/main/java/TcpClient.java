import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

//https://www.digitalocean.com/community/tutorials/java-socket-programming-server-client
public class TcpClient {

    public static void main(String[] args)
            throws  IOException, ClassNotFoundException, InterruptedException {
        Inet4Address host = (Inet4Address) Inet4Address.getLocalHost();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
     //   for (int i = 0; i < 9; i++)
          try(Socket socket = new Socket(host.getHostName(), 8080)) {
              socket.setSoTimeout(5000);
              oos = new ObjectOutputStream(socket.getOutputStream());
              System.out.println("Sending request to  server socket " + socket);
              if (oos.equals("exit"))
                  oos.writeObject("exit");
              else
                  oos.writeObject("Continue ");

              ois = new ObjectInputStream(socket.getInputStream());
              String message = (String) ois.readObject();
              System.out.println("Message " + message);
              ois.close();
              oos.close();
              Thread.sleep(100);
          } catch (SocketTimeoutException e) {
              System.out.println(" The socket timeout");
          } catch (IOException e) {
              System.out.println("Client error : " + e.getMessage());
          }
    }
}