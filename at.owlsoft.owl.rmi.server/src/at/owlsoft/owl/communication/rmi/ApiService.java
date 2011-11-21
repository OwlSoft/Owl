package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

public class ApiService extends UnicastRemoteObject implements IApiService
{
    private static final Logger logger           = Logger.getLogger(ApiService.class);

    private static final long   serialVersionUID = -3143956774086703476L;

    public ApiService() throws RemoteException
    {
        super();
    }

    @Override
    public IApiFactory createApiFactory() throws RemoteException
    {
        return new ApiFactory();
    }

    public static void startRmiService(String host, int port, String serviceName)
            throws RemoteException
    {

        System.setProperty("java.rmi.server.codebase", IApiFactory.class
                .getProtectionDomain().getCodeSource().getLocation().toString());

        IApiService service = new ApiService();

        java.rmi.registry.LocateRegistry.getRegistry(host, port).rebind(
                serviceName, service);
    }

    public static void main(String[] args)
    {
        try
        {
            logger.info("Starting service");
            ApiService.startRmiService("localhost", Registry.REGISTRY_PORT,
                    IApiService.DEFAULT_RMI_NAME);

            logger.info("Service registered");
        }
        catch (Exception e)
        {
            logger.error("Could not start service", e);
        }
    }
}
