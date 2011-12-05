package at.owlsoft.owl.communication.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import at.owlsoft.owl.model.NoPermissionException;

public interface IConfigurationApi extends Remote
{

    Map<String, String> getAllProperties() throws NoPermissionException,
            RemoteException;

    void setAll(Map<String, String> propertiese) throws NoPermissionException,
            RemoteException;

    void store() throws IOException, NoPermissionException, RemoteException;

    void removeProperties(List<String> properties)
            throws NoPermissionException, RemoteException;

}
