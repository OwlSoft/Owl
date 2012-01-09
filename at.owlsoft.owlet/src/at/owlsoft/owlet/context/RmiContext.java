//package at.owlsoft.owlet.context;
//
//import java.rmi.AccessException;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//
//import at.owlsoft.owl.communication.rmi.IApiFactory;
//import at.owlsoft.owl.communication.rmi.IApiService;
//
//public class RmiContext
//{
//    private static RmiContext _instance;
//
//    public static RmiContext getInstance()
//    {
//        return _instance;
//    }
//
//    public static void initialize(String host, int port, String serviceName)
//            throws AccessException, RemoteException, NotBoundException
//    {
//        _instance = new RmiContext(host, port, serviceName);
//    }
//
//    private IApiFactory _factory;
//
//    public IApiFactory getFactory()
//    {
//        return _factory;
//    }
//
//    private RmiContext(String host, int port, String serviceName)
//            throws AccessException, RemoteException, NotBoundException
//    {
//        IApiService service = (IApiService) LocateRegistry.getRegistry(host,
//                port).lookup(serviceName);
//        _factory = service.createApiFactory();
//    }
// }
