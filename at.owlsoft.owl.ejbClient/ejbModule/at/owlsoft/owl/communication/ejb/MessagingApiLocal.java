package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface MessagingApiLocal extends ILocalApiBase
{
    @Override
    MessagingApiRemote getRemoteObject();
}
