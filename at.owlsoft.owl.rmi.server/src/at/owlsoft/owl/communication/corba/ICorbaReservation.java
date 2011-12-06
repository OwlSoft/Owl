package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.accounting.ICorbaReservationPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;

public class ICorbaReservation extends ICorbaReservationPOA
{

    @Override
    public int getDesiredDuration()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getUUID()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getStartDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ICorbaActivityStatusEntry[] getActivityStatusEntries()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMediumExemplar getMediumExemplar()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getCustomer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getCreator()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMedium getMedium()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
