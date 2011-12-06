package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityPOA;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.model.accounting.IActivity;

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
        // TODO Auto-generated method stub
        return _activity.getUUID().toString();
    }

    @Override
    public long getStartDate()
    {
        // TODO Auto-generated method stub
        return _activity.getStartDate().getTime();
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
