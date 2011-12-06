package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityHelper;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRentalHelper;
import at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntry;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntryHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.media.IMediumExemplarStatusEntry;

public class CorbaMediumExemplare extends ICorbaMediumExemplarPOA
{

    private IMediumExemplar _mediumExemplar;
    private POA             _rootPOA;

    @Override
    public int getExemplarID()
    {
        return _mediumExemplar.getExemplarID();
    }

    @Override
    public String getMetaData(String key)
    {
        return (String) _mediumExemplar.getMetaData(key);
    }

    @Override
    public String[] getMetaDataKeys()
    {
        return _mediumExemplar.getMetaDataKeys().toArray(new String[0]);
    }

    @Override
    public ICorbaMedium getMedium()
    {
        try
        {
            CorbaMedium cMedium = new CorbaMedium();
            cMedium.setMedium(_mediumExemplar.getMedium());
            cMedium.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref;
            ref = _rootPOA.servant_to_reference(cMedium);
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

    @Override
    public ICorbaMediumExemplarStatusEntry[] getMediumExemplarStatusEntries()
    {
        try
        {
            ICorbaMediumExemplarStatusEntry[] temp = new ICorbaMediumExemplarStatusEntry[_mediumExemplar
                    .getMediumExemplarStatusEntries().size()];
            int index = 0;
            for (IMediumExemplarStatusEntry mse : _mediumExemplar
                    .getMediumExemplarStatusEntries())
            {
                CorbaMediumExemplarStatusEntry cmse = new CorbaMediumExemplarStatusEntry();
                cmse.setMediumExemplarStatusEntry(mse);
                cmse.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cmse);
                temp[index] = ICorbaMediumExemplarStatusEntryHelper.narrow(ref);
                index++;
            }
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
    public ICorbaActivity[] getActivities()
    {
        try
        {
            ICorbaActivity[] temp = new ICorbaActivity[_mediumExemplar
                    .getActivities().size()];
            int index = 0;
            for (IActivity activity : _mediumExemplar.getActivities())
            {
                CorbaActivity cactivity = new CorbaActivity();
                cactivity.setActivity(activity);
                cactivity.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cactivity);
                temp[index] = ICorbaActivityHelper.narrow(ref);
                index++;
            }
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
    public ICorbaRental getLastRental()
    {
        try
        {
            CorbaRental cRental = new CorbaRental();
            cRental.setRental(_mediumExemplar.getLastRental());
            cRental.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref;
            ref = _rootPOA.servant_to_reference(cRental);
            return ICorbaRentalHelper.narrow(ref);
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
    public CorbaMediumExemplarStatus getCurrentState()
    {
        switch (_mediumExemplar.getCurrentState())
        {
            case Rentable:
                return CorbaMediumExemplarStatus.Rentable;
            case Rented:
                return CorbaMediumExemplarStatus.Rented;
            case Returned:
                return CorbaMediumExemplarStatus.Returned;
            case StockItem:
                return CorbaMediumExemplarStatus.StockItem;
            default:
                throw new RuntimeException("Unknown CorbaMediumExemplarStatus");
        }

    }

    public void setMediumExemplare(IMediumExemplar mediumExemplar)
    {
        _mediumExemplar = mediumExemplar;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }
}
