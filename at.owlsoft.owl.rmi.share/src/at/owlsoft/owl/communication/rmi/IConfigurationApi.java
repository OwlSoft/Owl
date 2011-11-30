package at.owlsoft.owl.communication.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.util.List;
import java.util.Map;

import at.owlsoft.owl.model.NoPermissionException;

public interface IConfigurationApi extends Remote
{

    Map<String, String> getAllProperties() throws NoPermissionException;

    void setAll(Map<String, String> propertiese) throws NoPermissionException;

    void store() throws IOException, NoPermissionException;

    void removeProperties(List<String> properties) throws NoPermissionException;

}
