package at.owlsoft.owlet;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import at.owlsoft.owl.ISearchApi;

public class Client
{
    public static void main(String[] args)
    {
        ISearchApi stub = null;

        try
        {

            String host = "localhost";
            Integer port = 1099;
            String name = "userRmi";

            if (args.length > 0)
            {
                host = args[0];

                if (args.length > 1)
                {
                    port = Integer.parseInt(args[1]);

                    if (args.length > 2)
                    {
                        name = args[2];
                    }
                }
            }

            try
            {

                stub = (ISearchApi) java.rmi.registry.LocateRegistry
                        .getRegistry(host, port).lookup(name);

                System.out.println("Output: " + stub.getName());

                stub.setName("Daniel");

                // stub = (ISearchApi) java.rmi.registry.LocateRegistry
                // .getRegistry(host, port).lookup(name);

                System.out.println("Output: " + stub.getName());

                System.out.println("Output: "
                        + stub.getObjectDispatcher().getDispatcherName());

                stub.getObjectDispatcher().setDispatcherName("neuerName");

                // stub = (ISearchApi) java.rmi.registry.LocateRegistry
                // .getRegistry(host, port).lookup(name);

                System.out.println("Output: "
                        + stub.getObjectDispatcher().getDispatcherName());

                System.out.println("Output: "
                        + stub.getObjectDispatcher().getToDispatch()
                                .getFirstName());

                stub.getObjectDispatcher().getToDispatch()
                        .setFirstName("neuerUserName");

                // stub = (ISearchApi) java.rmi.registry.LocateRegistry
                // .getRegistry(host, port).lookup(name);

                System.out.println("Output: "
                        + stub.getObjectDispatcher().getToDispatch()
                                .getFirstName());

            }
            catch (NumberFormatException e)
            {
                System.out.println("Keine Zahl eingegeben");
            }
        }
        catch (RemoteException e)
        {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        catch (NotBoundException e)
        {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
