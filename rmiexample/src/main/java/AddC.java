import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddC extends UnicastRemoteObject implements AddI {

    public AddC()throws RemoteException {
        super();
    }
    public  int hello() {
        String print = "Hello ";
        return print.indexOf(print) ;
    }

}
