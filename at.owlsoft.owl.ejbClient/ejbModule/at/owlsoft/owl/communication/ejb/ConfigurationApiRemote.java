package at.owlsoft.owl.communication.ejb;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import at.owlsoft.owl.model.NoPermissionException;

@Remote
public interface ConfigurationApiRemote extends IApiBase
{
    public static final String JNDI_NAME = "owl/ConfigurationApiRemote";

    Map<String, String> getAllProperties() throws NoPermissionException;

    void setAll(Map<String, String> propertiese) throws NoPermissionException;

    void store() throws IOException, NoPermissionException;

    void removeProperties(List<String> properties) throws NoPermissionException;
}
