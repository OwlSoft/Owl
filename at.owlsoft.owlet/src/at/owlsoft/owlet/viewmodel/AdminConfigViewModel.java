package at.owlsoft.owlet.viewmodel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import at.owlsoft.owl.communication.ejb.ConfigurationApiRemote;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owlet.context.EjbContext;

public class AdminConfigViewModel
{

    private ConfigurationApiRemote _configApi;

    public void initialize() throws InvalidOperationException
    {
        try
        {
            _configApi = EjbContext.getInstance().getFactory()
                    .createConfigurationApi();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }
    }

    public Map<String, String> getAllProperties()
            throws InvalidOperationException
    {
        try
        {
            // initialize() not yet called?
            if (_configApi == null)
            {
                _configApi = EjbContext.getInstance().getFactory()
                        .createConfigurationApi();
            }

            return _configApi.getAllProperties();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            throw new NullPointerException("No data available.");
        }
        catch (NoPermissionException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Could not fetch data.", e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Server error", e);
        }
    }

    public void setAll(Map<String, String> propertiese)
            throws InvalidOperationException
    {
        try
        {
            _configApi.setAll(propertiese);
        }
        catch (NoPermissionException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Could not fetch data.", e);
        }
    }

    public void store()
    {
        try
        {
            _configApi.store();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Could not save data.", e);
        }
        catch (NoPermissionException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Could not save data.", e);
        }
    }

    public void removeProperties(List<String> properties)
            throws InvalidOperationException
    {
        try
        {
            _configApi.removeProperties(properties);
        }
        catch (NoPermissionException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Could not save data.", e);
        }

    }

}
