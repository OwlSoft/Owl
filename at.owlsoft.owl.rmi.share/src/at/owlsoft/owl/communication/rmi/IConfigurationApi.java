package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.util.Map;

public interface IConfigurationApi extends Remote
{

    Map<String, String> getAllProperties();

    void setAll(Map<String, String> propertiese);

}
