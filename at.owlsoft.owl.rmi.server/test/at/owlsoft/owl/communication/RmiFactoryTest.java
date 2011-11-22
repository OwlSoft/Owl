package at.owlsoft.owl.communication;

import java.rmi.registry.Registry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.owlsoft.owl.communication.rmi.ApiService;
import at.owlsoft.owl.communication.rmi.IApiFactory;
import at.owlsoft.owl.communication.rmi.IApiService;
import at.owlsoft.owl.communication.rmi.IExtendApi;

public class RmiFactoryTest
{
    @Before
    public void setup() throws Exception
    {
        java.rmi.registry.LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        ApiService.startRmiService("localhost", Registry.REGISTRY_PORT,
                IApiService.DEFAULT_RMI_NAME);
    }

    public void tearDown() throws Exception
    {
        Registry registry = java.rmi.registry.LocateRegistry
                .getRegistry(Registry.REGISTRY_PORT);

        for (String key : registry.list())
        {
            registry.unbind(key);
        }
    }

    @Test
    public void testRmiFactory() throws Exception
    {
        IApiService service = (IApiService) java.rmi.registry.LocateRegistry
                .getRegistry(Registry.REGISTRY_PORT).lookup(
                        IApiService.DEFAULT_RMI_NAME);

        IApiFactory apiFactory = service.createApiFactory();

        assertApi(IExtendApi.class, apiFactory.createExtendApi());
        assertApi(IExtendApi.class, apiFactory.createRentalApi());
        assertApi(IExtendApi.class, apiFactory.createReservationApi());
        assertApi(IExtendApi.class, apiFactory.createReturnApi());
        assertApi(IExtendApi.class, apiFactory.createSearchApi());
    }

    private void assertApi(Class<?> expectedClass, Object instance)
    {
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance.getClass().toString(),
                expectedClass.isInstance(instance));
    }
}
