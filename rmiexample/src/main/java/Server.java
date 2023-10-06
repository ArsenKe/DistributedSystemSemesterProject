import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        AddC obj = new AddC();
        Naming.rebind("ADD",obj);
        System.out.println("Server started");


    }


}
