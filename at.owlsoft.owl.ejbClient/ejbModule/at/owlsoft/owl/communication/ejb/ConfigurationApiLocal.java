package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface ConfigurationApiLocal extends ILocalApiBase
{
    @Override
    ConfigurationApiRemote getRemoteObject();
}
