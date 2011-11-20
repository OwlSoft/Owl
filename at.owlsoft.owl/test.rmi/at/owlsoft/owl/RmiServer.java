package at.owlsoft.owl;

import java.rmi.AccessException;
import java.rmi.RemoteException;

public class RmiServer
{

    public static void main(String[] args)
    {
        System.setProperty("java.rmi.server.codebase", ISearchApi.class
                .getProtectionDomain().getCodeSource().getLocation().toString());

        RmiSearchApi obj;
        try
        {
            obj = new RmiSearchApi();

            try
            {
                java.rmi.registry.LocateRegistry.getRegistry().rebind(
                        "userRmi", obj);
            }
            catch (AccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (RemoteException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch (RemoteException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // starting registry at default port

        System.out.println("RMI registry ready.");
    }

}
