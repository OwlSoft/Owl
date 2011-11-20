package at.owlsoft.owl.communication;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import at.owlsoft.owl.communication.rmi.ApiFactory;
import at.owlsoft.owl.communication.rmi.IApiFactory;

public class RmiFactoryTest
{

    @Test
    public void testRmiFactory()
    {
        try
        {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            ApiFactory factory = new ApiFactory();
            factory.startRmiService(IApiFactory.DEFAULT_RMI_NAME, 1099);

            IApiFactory stub = (IApiFactory) java.rmi.registry.LocateRegistry
                    .getRegistry("localhost", 1099).lookup(
                            IApiFactory.DEFAULT_RMI_NAME);

            Assert.assertNotNull(stub.createExtendApi());
            Assert.assertNotNull(stub.createRentalApi());
            Assert.assertNotNull(stub.createReservationApi());
            Assert.assertNotNull(stub.createReturnApi());
            Assert.assertNotNull(stub.createSearchApi());

        }
        catch (RemoteException exception)
        {

        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
