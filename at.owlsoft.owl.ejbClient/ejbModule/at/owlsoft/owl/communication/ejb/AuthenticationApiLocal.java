package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface AuthenticationApiLocal extends ILocalApiBase
{
    @Override
    AuthenticationApiRemote getRemoteObject();
}
