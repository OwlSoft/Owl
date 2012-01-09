package at.owlsoft.owl.communication.ejb;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import at.owlsoft.owl.model.NoPermissionException;

/**
 * Session Bean implementation class ConfigurationApi
 */
@Stateful(mappedName = ConfigurationApi.JNDI_NAME)
public class ConfigurationApi extends ApiBase implements ConfigurationApiRemote
{
    public ConfigurationApi()
    {

    }

    @PostConstruct
    public void init()
    {
    }

    @Override
    public void reset()
    {

    }

    @Override
    public Map<String, String> getAllProperties() throws NoPermissionException
    {
        return getContext().getConfigurationController().getAllProperties();
    }

    @Override
    public void setAll(Map<String, String> properties)
            throws NoPermissionException
    {
        getContext().getConfigurationController().setAll(properties);
    }

    @Override
    public void store() throws NoPermissionException, IOException
    {
        getContext().getConfigurationController().store();
    }

    @Override
    public void removeProperties(List<String> properties)
            throws NoPermissionException
    {
        getContext().getConfigurationController().removeProperty(properties);
    }
}
