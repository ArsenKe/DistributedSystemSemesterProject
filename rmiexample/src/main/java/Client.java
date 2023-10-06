import java.net.MalformedURLException;
import java.rmi.*;

public class Client extends AddC {

    public Client() throws Exception {
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

        AddI obj1 = (AddI) Naming.lookup("hello");
        int name = obj1.hello();
        System.out.println("addition is :  " + name);



    }



}
