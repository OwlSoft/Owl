package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface SearchApiLocal extends ILocalApiBase
{
    @Override
    SearchApiRemote getRemoteObject();
}
