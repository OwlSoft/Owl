package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntryHelper;
import at.owlsoft.owl.corbamodel.accounting.ICorbaReservationPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.model.accounting.IActivityStatusEntry;
import at.owlsoft.owl.model.accounting.IReservation;

public class CorbaReservation extends ICorbaReservationPOA
{

    private POA          _rootPOA;
    private IReservation _reservation;

    public void setReservation(IReservation reservation)
    {
        _reservation = reservation;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public int getDesiredDuration()
    {
        return _reservation.getDesiredDuration();
    }

    @Override
    public String getUUID()
    {
        return _reservation.getUUID().toString();
    }

    @Override
    public long getStartDate()
    {
        return _reservation.getStartDate().getTime();
    }

    @Override
    public ICorbaActivityStatusEntry[] getActivityStatusEntries()
    {
        try
        {
            ICorbaActivityStatusEntry[] temp = new ICorbaActivityStatusEntry[_reservation
                    .getActivityStatusEntries().size()];
            int index = 0;
            for (IActivityStatusEntry entry : _reservation
                    .getActivityStatusEntries())
            {
                CorbaActivityStatusEntry cEntry = new CorbaActivityStatusEntry();
                cEntry.setActivityStatusEntry(entry);
                cEntry.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cEntry);
                temp[index] = ICorbaActivityStatusEntryHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaMediumExemplar getMediumExemplar()
    {
        try
        {
            CorbaMediumExemplare copy = new CorbaMediumExemplare();
            copy.setMediumExemplare(_reservation.getMediumExemplar());
            copy.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(copy);
            return ICorbaMediumExemplarHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaSystemUser getCustomer()
    {
        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setUser(_reservation.getCustomer());
            cuser.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cuser);
            return ICorbaSystemUserHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaSystemUser getCreator()
    {
        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setUser(_reservation.getCreator());
            cuser.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cuser);
            return ICorbaSystemUserHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaMedium getMedium()
    {
        try
        {
            CorbaMedium medium = new CorbaMedium();
            medium.setMedium(_reservation.getMedium());
            medium.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(medium);
            return ICorbaMediumHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
