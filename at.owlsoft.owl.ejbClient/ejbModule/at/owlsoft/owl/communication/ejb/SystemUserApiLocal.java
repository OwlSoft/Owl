package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface SystemUserApiLocal extends ILocalApiBase
{
    @Override
    SystemUserApiRemote getRemoteObject();
}
