package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.Map;

import at.owlsoft.owl.business.ConfigurationController;

public class ConfigurationApi extends ApiBase implements IConfigurationApi
{

    /**
     * 
     */
    private static final long       serialVersionUID = -9142031568201807374L;

    private ConfigurationController _configurationController;

    public ConfigurationApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _configurationController = factory.getContext()
                .getConfigurationController();
    }

    public Map<String, String> getAllProperties()
    {
        return _configurationController.getAllProperties();
    }

    public void setAll(Map<String, String> properties)
    {
        _configurationController.setAll(properties);
    }
}
