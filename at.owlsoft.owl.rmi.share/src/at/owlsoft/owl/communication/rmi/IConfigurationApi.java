package at.owlsoft.owl.communication.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface IConfigurationApi extends Remote
{

    Map<String, String> getAllProperties();

    void setAll(Map<String, String> propertiese);

    void store() throws IOException;

    void removeProperties(List<String> properties);

}
