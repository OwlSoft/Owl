package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface RentalApiLocal extends ILocalApiBase
{
    @Override
    RentalApiRemote getRemoteObject();
}
