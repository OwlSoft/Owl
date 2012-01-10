package at.owlsoft.owl.communication.ejb;

import javax.ejb.Local;

@Local
public interface ReservationApiLocal extends ILocalApiBase
{
    @Override
    ReservationApiRemote getRemoteObject();
}
