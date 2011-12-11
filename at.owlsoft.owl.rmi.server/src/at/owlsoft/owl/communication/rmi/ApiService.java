package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import at.owlsoft.owl.Owl;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldCategory;

public class ApiService extends UnicastRemoteObject implements IApiService
{

    private static final String LOG4J_CONFIGURATION = "/log4j.xml";
    private static final Logger logger              = Logger.getLogger(ApiService.class);

    private static final long   serialVersionUID    = -3143956774086703476L;

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
        StringBuilder codeBase = new StringBuilder();
        codeBase.append(getCodeBaseUrl(SearchFieldCategory.class));
        codeBase.append(" ");
        codeBase.append(getCodeBaseUrl(ISearchFieldCategory.class));
        codeBase.append(" ");
        codeBase.append(getCodeBaseUrl(IApiFactory.class));

        System.setProperty("java.rmi.server.codebase", codeBase.toString());

        IApiService service = new ApiService();

        java.rmi.registry.LocateRegistry.getRegistry(host, port).rebind(
                serviceName, service);

        // Server context
        OwlApplicationContext serverContext = new OwlApplicationContext();
        serverContext.getOverdueCheckController().startChecker();
    }

    private static Object getCodeBaseUrl(Class<?> clz)
    {
        return clz.getProtectionDomain().getCodeSource().getLocation()
                .toString();
    }

    public static void main(String[] args)
    {
        System.setSecurityManager(new AllPermissionSecurityManager());

        try
        {
            DOMConfigurator.configure(Owl.class
                    .getResource(LOG4J_CONFIGURATION));

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
