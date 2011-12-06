package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntryHelper;
import at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtension;
import at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtensionHelper;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRentalPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.model.accounting.IActivityStatusEntry;
import at.owlsoft.owl.model.accounting.IFilingExtension;
import at.owlsoft.owl.model.accounting.IRental;

public class CorbaRental extends ICorbaRentalPOA
{

    private POA     _rootPOA;
    private IRental _rental;

    @Override
    public long getEndDate()
    {
        return _rental.getEndDate().getTime();
    }

    @Override
    public int getFilingExtensionCount()
    {
        return _rental.getFilingExtensionCount();
    }

    @Override
    public ICorbaFilingExtension[] getFilingExtensions()
    {
        try
        {
            ICorbaFilingExtension[] temp = new ICorbaFilingExtension[_rental
                    .getFilingExtensions().size()];
            int index = 0;
            for (IFilingExtension filingExtension : _rental
                    .getFilingExtensions())
            {
                CorbaFilingExtension cfilingExt = new CorbaFilingExtension();
                cfilingExt.setFilingExtension(filingExtension);
                cfilingExt.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cfilingExt);
                temp[index] = ICorbaFilingExtensionHelper.narrow(ref);
            }
            index++;
            return temp;
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
    }

    @Override
    public String getUUID()
    {
        return _rental.getUUID().toString();
    }

    @Override
    public long getStartDate()
    {
        return _rental.getStartDate().getTime();
    }

    @Override
    public ICorbaActivityStatusEntry[] getActivityStatusEntries()
    {
        try
        {
            ICorbaActivityStatusEntry[] temp = new ICorbaActivityStatusEntry[_rental
                    .getActivityStatusEntries().size()];
            int index = 0;
            for (IActivityStatusEntry ase : _rental.getActivityStatusEntries())
            {
                CorbaActivityStatusEntry cfilingExt = new CorbaActivityStatusEntry();
                cfilingExt.setActivityStatusEntry(ase);
                cfilingExt.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cfilingExt);
                temp[index] = ICorbaActivityStatusEntryHelper.narrow(ref);
            }
            index++;
            return temp;
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

    }

    @Override
    public ICorbaMediumExemplar getMediumExemplar()
    {
        try
        {
            CorbaMediumExemplare cMedium = new CorbaMediumExemplare();
            cMedium.setMediumExemplare(_rental.getMediumExemplar());
            cMedium.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cMedium);
            return ICorbaMediumExemplarHelper.narrow(ref);
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
    }

    @Override
    public ICorbaSystemUser getCustomer()
    {
        CorbaSystemUser cuser = new CorbaSystemUser();
        cuser.setUser(_rental.getCustomer());
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
        CorbaSystemUser cuser = new CorbaSystemUser();
        cuser.setUser(_rental.getCustomer());
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
    public ICorbaMedium getMedium()
    {
        try
        {
            CorbaMedium cuser = new CorbaMedium();
            cuser.setMedium(_rental.getMedium());
            cuser.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cuser);
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

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    public void setRental(IRental rental)
    {
        _rental = rental;
    }

}
