package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityPOA;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntryHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.accounting.IActivityStatusEntry;

public class CorbaActivity extends ICorbaActivityPOA
{

    private IActivity _activity;
    private POA       _rootPOA;

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public String getUUID()
    {
        return _activity.getUUID().toString();
    }

    @Override
    public long getStartDate()
    {
        return _activity.getStartDate().getTime();
    }

    @Override
    public ICorbaActivityStatusEntry[] getActivityStatusEntries()
    {
        try
        {
            ICorbaActivityStatusEntry[] temp = new ICorbaActivityStatusEntry[_activity
                    .getActivityStatusEntries().size()];
            int index = 0;
            for (IActivityStatusEntry ase : _activity
                    .getActivityStatusEntries())
            {
                CorbaActivityStatusEntry cAse = new CorbaActivityStatusEntry();
                cAse.setActivityStatusEntry(ase);
                cAse.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cAse);
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
            CorbaMediumExemplare cMediumExemplar = new CorbaMediumExemplare();
            cMediumExemplar.setMediumExemplare(_activity.getMediumExemplar());
            cMediumExemplar.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(cMediumExemplar);
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
        CorbaSystemUser cuser = new CorbaSystemUser();
        cuser.setUser(_activity.getCustomer());
        cuser.setRootPOA(_rootPOA);

        org.omg.CORBA.Object ref;
        try
        {
            ref = _rootPOA.servant_to_reference(cuser);
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
        return ICorbaSystemUserHelper.narrow(ref);
    }

    @Override
    public ICorbaSystemUser getCreator()
    {

        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setUser(_activity.getCreator());
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
            medium.setMedium(_activity.getMedium());
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

    public void setActivity(IActivity activity)
    {
        _activity = activity;
    }

}
