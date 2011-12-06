package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityPOA;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ICorbaSystemUserHelper.narrow(ref);
    }

    @Override
    public ICorbaSystemUser getCreator()
    {
        //
        return null;
    }

    @Override
    public ICorbaMedium getMedium()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void setActivity(IActivity activity)
    {
        // TODO Auto-generated method stub
        _activity = activity;
    }

}
